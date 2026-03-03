
import sys
import os
import json

# Add project python directory to path
sys.path.append(r'c:\Users\prsnl\OneDrive\Desktop\android\app\src\main\python')

import intent_classifier
import coding_engine
import cyber_engine
import resume_engine
import startup_engine
import general_engine

def test_pipeline(input_text, expected_module):
    print(f"\n--- Testing Input: '{input_text[:50]}...' ---")
    
    # 1. Classification
    raw_class_result = intent_classifier.classify_input(input_text)
    class_result = json.loads(raw_class_result)
    
    detected_module = class_result.get("module")
    confidence = class_result.get("confidence")
    
    print(f"Detected Module: {detected_module} (Score: {confidence})")
    
    if detected_module != expected_module and expected_module != "any":
        print(f"!!! WARNING: Expected {expected_module}, got {detected_module}")
    
    # 2. Engine Execution
    engine_output = "{}"
    if detected_module == "coding":
        engine_output = coding_engine.analyze_code(input_text)
    elif detected_module == "cybersecurity":
        engine_output = cyber_engine.analyze_security(input_text)
    elif detected_module == "resume":
        engine_output = resume_engine.analyze_resume(input_text)
    elif detected_module == "startup":
        engine_output = startup_engine.analyze_startup(input_text)
    else:
        engine_output = general_engine.analyze_general(input_text)
    
    # 3. Verify Standardized Structure
    try:
        data = json.loads(engine_output)
        required_fields = ["module_name", "confidence_score", "reasoning", "structured_analysis", "risk_score", "timestamp"]
        missing = [f for f in required_fields if f not in data]
        
        if missing:
            print(f"!!! FAIL: Missing standardized fields: {missing}")
        else:
            print(f"PASS: Standardized structure verified for {data['module_name']}")
            print(f"Risk Score: {data['risk_score']}")
            print(f"Reasoning Steps: {len(data['reasoning'])}")
    except Exception as e:
        print(f"!!! FAIL: Could not parse engine output JSON: {e}")

if __name__ == "__main__":
    test_pipeline("def hello(): print('world')", "coding")
    test_pipeline("SELECT * FROM users WHERE pass = '123' OR 1=1", "cybersecurity")
    test_pipeline("Experience: Java Developer at Google for 10 years", "resume")
    test_pipeline("I want to build an AI platform for healthtech tracking", "startup")
    test_pipeline("Hello how are you doing today?", None) # Should trigger general/none
