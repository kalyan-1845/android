import sys
import json
import importlib

def main():
    if len(sys.argv) < 3:
        print(json.dumps({"status": "error", "message": "Usage: run.py <engine> <function> <input>"}))
        return

    engine_name = sys.argv[1]
    func_name = sys.argv[2]
    user_input = sys.argv[3]

    try:
        # Load the module
        module = importlib.import_module(engine_name)
        
        # Get the function
        func = getattr(module, func_name)
        
        # Call the function
        result = func(user_input)
        
        # Output result (should be JSON)
        print(result)
        
    except Exception as e:
        print(json.dumps({"status": "error", "message": f"Execution error: {str(e)}"}))

if __name__ == "__main__":
    main()
