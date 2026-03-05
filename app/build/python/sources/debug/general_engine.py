"""
OmniAgent — General Context Handler
Provides fallback analysis for general input that doesn't match specialized modules.
"""

import json
from datetime import datetime

class GeneralEngine:
    def analyze(self, input_text):
        words = input_text.split()
        report = {
            "module_name": "General Context Handler",
            "confidence_score": 0.5,
            "reasoning": [
                "Input did not meet similarity thresholds for specialized modules",
                "Performing general semantic analysis",
                "Detected {} total words in query".format(len(words))
            ],
            "structured_analysis": {
                "word_count": len(words),
                "summary": "This appears to be a general query. For specialized analysis, try asking about coding, security, resumes, or startups.",
                "sentiment_hints": "Nuertral/Informational",
                "suggestions": [
                    "Ask: 'Analyze this Python script for bugs'",
                    "Ask: 'Check this SQL query for injection vulnerabilities'",
                    "Ask: 'Score my resume for a developer role'",
                    "Ask: 'Evaluate my startup idea for a food delivery app'"
                ]
            },
            "risk_score": 0.0,
            "timestamp": datetime.now().strftime("%Y-%m-%d %H:%M:%S")
        }
        return json.dumps(report, indent=2)

_engine = None
def get_engine():
    global _engine
    if _engine is None:
        _engine = GeneralEngine()
    return _engine

def analyze_general(input_text):
    return get_engine().analyze(input_text)
