"""
OmniAgent — Cybersecurity Detection Engine
Detects SQL injection, XSS, and common vulnerabilities in user-provided input/code.
Fully offline, returns structured JSON reports.
"""

import re
import json
from datetime import datetime


class CyberSecurityEngine:
    """Detects common web security vulnerabilities using pattern matching."""

    # === DETECTION PATTERNS ===

    SQLI_PATTERNS = [
        (r"(?i)(\b(SELECT|INSERT|UPDATE|DELETE|DROP|UNION|ALTER|CREATE)\b\s+.*(FROM|INTO|TABLE|SET|WHERE))",
         "SQL keyword sequence detected", "high"),
        (r"(?i)(('|\")\s*(OR|AND)\s*('|\")\s*=\s*('|\"))",
         "Classic SQLi tautology pattern: ' OR '='", "critical"),
        (r"(?i)(--\s*$|;\s*--)",
         "SQL comment injection (--)", "high"),
        (r"(?i)(\bUNION\s+SELECT\b)",
         "UNION SELECT injection attempt", "critical"),
        (r"(?i)(1\s*=\s*1|1\s*=\s*'1'|'1'\s*=\s*'1')",
         "Tautology condition (1=1)", "high"),
        (r"(?i)(\bDROP\s+TABLE\b)",
         "DROP TABLE attempt", "critical"),
        (r"(?i)(\bEXEC\s*\(|EXECUTE\s*\()",
         "Dynamic SQL execution", "high"),
        (r"(?i)(LOAD_FILE|INTO\s+OUTFILE|INTO\s+DUMPFILE)",
         "File operation via SQL", "critical"),
        (r"(?i)(BENCHMARK\s*\(|SLEEP\s*\(|WAITFOR\s+DELAY)",
         "Time-based blind SQLi", "high"),
        (r"(?i)(CHAR\s*\(\s*\d+\s*(,\s*\d+\s*)*\))",
         "CHAR() encoding — possible obfuscation", "medium"),
    ]

    XSS_PATTERNS = [
        (r"<\s*script[^>]*>",
         "Script tag injection", "critical"),
        (r"(?i)(on(load|error|click|mouseover|focus|blur|submit|change|input)\s*=)",
         "Event handler injection (onload/onerror/etc)", "high"),
        (r"(?i)(javascript\s*:)",
         "JavaScript protocol injection", "critical"),
        (r"(?i)(document\.(cookie|location|write|domain))",
         "DOM manipulation attempt", "high"),
        (r"(?i)(alert\s*\(|confirm\s*\(|prompt\s*\()",
         "Alert/prompt injection", "medium"),
        (r"<\s*iframe[^>]*>",
         "Iframe injection", "high"),
        (r"<\s*img[^>]*\s+onerror\s*=",
         "Image tag with onerror handler", "high"),
        (r"(?i)(eval\s*\(|setTimeout\s*\(|setInterval\s*\()",
         "Dynamic code execution (eval/setTimeout)", "high"),
        (r"<\s*svg[^>]*\s+on\w+\s*=",
         "SVG injection with event handler", "high"),
        (r"(?i)(fromCharCode|String\.fromCharCode)",
         "Character encoding bypass attempt", "medium"),
    ]

    GENERAL_PATTERNS = [
        (r"(?i)(\.\./\.\./|%2e%2e%2f|%2e%2e/)",
         "Directory traversal attempt", "high"),
        (r"(?i)(\/etc\/passwd|\/etc\/shadow|\/proc\/self)",
         "Linux system file access attempt", "critical"),
        (r"(?i)(cmd\.exe|/bin/(sh|bash)|powershell)",
         "Command injection — shell access", "critical"),
        (r"(?i)(\|\s*\w+|\$\(.*\)|`.*`)",
         "Command substitution / pipe injection", "high"),
        (r"(?i)(base64_decode|atob\s*\()",
         "Base64 decoding — possible obfuscation", "medium"),
        (r"(?i)(phpinfo\s*\(\)|system\s*\(|passthru\s*\(|exec\s*\()",
         "Server-side code execution attempt", "critical"),
        (r"(?i)(LDAP|ldap://)",
         "LDAP injection attempt", "high"),
        (r"(?i)(xmlns|ENTITY\s+\w+)",
         "XML external entity (XXE) indicators", "high"),
    ]

    def __init__(self):
        self.scan_log = []

    def _log(self, message):
        self.scan_log.append({
            "timestamp": datetime.now().strftime("%H:%M:%S"),
            "event": message
        })

    def analyze(self, input_text):
        """
        Main analysis entry point.
        Scans input text for security vulnerabilities.
        """
        self.scan_log = []
        self._log("Starting security scan")
        self._log("Input length: {} characters".format(len(input_text)))

        findings = []

        # SQL Injection scan
        sqli_findings = self._scan_patterns(input_text, self.SQLI_PATTERNS, "SQL Injection")
        findings.extend(sqli_findings)
        self._log("SQLi scan: {} findings".format(len(sqli_findings)))

        # XSS scan
        xss_findings = self._scan_patterns(input_text, self.XSS_PATTERNS, "Cross-Site Scripting (XSS)")
        findings.extend(xss_findings)
        self._log("XSS scan: {} findings".format(len(xss_findings)))

        # General vulnerability scan
        gen_findings = self._scan_patterns(input_text, self.GENERAL_PATTERNS, "General Vulnerability")
        findings.extend(gen_findings)
        self._log("General scan: {} findings".format(len(gen_findings)))

        # Compute risk score
        risk_score = self._compute_risk_score(findings)

        # Build report
        report = {
            "engine": "Cybersecurity Detection Engine",
            "timestamp": datetime.now().strftime("%Y-%m-%d %H:%M:%S"),
            "status": "success",
            "input_length": len(input_text),
            "total_findings": len(findings),
            "risk_score": risk_score,
            "risk_level": self._risk_level(risk_score),
            "findings": findings,
            "summary": {
                "sqli_count": len(sqli_findings),
                "xss_count": len(xss_findings),
                "general_count": len(gen_findings),
                "critical": sum(1 for f in findings if f["severity"] == "critical"),
                "high": sum(1 for f in findings if f["severity"] == "high"),
                "medium": sum(1 for f in findings if f["severity"] == "medium"),
            },
            "recommendations": self._generate_recommendations(findings),
            "log": self.scan_log
        }

        return json.dumps(report, indent=2)

    def _scan_patterns(self, text, patterns, category):
        """Scan text against a list of regex patterns."""
        findings = []
        for pattern, description, severity in patterns:
            matches = re.findall(pattern, text)
            if matches:
                # Find line numbers of matches
                lines_found = []
                for i, line in enumerate(text.splitlines(), 1):
                    if re.search(pattern, line):
                        lines_found.append(i)

                findings.append({
                    "category": category,
                    "description": description,
                    "severity": severity,
                    "match_count": len(matches),
                    "lines": lines_found[:5],  # Limit to first 5 lines
                    "sample": str(matches[0])[:80] if matches else ""
                })
        return findings

    def _compute_risk_score(self, findings):
        """Compute risk score out of 100 (higher = more dangerous)."""
        score = 0
        for f in findings:
            if f["severity"] == "critical":
                score += 25
            elif f["severity"] == "high":
                score += 15
            elif f["severity"] == "medium":
                score += 8
        return min(100, score)

    def _risk_level(self, score):
        """Convert numeric score to risk level string."""
        if score >= 75:
            return "CRITICAL"
        elif score >= 50:
            return "HIGH"
        elif score >= 25:
            return "MEDIUM"
        elif score > 0:
            return "LOW"
        return "CLEAN"

    def _generate_recommendations(self, findings):
        """Generate security recommendations based on findings."""
        recs = []
        categories = set(f["category"] for f in findings)

        if "SQL Injection" in categories:
            recs.extend([
                "Use parameterized queries / prepared statements instead of string concatenation",
                "Implement input validation and whitelist-based filtering",
                "Apply least privilege database permissions"
            ])

        if "Cross-Site Scripting (XSS)" in categories:
            recs.extend([
                "Encode all output using context-appropriate encoding (HTML, JS, URL)",
                "Implement Content Security Policy (CSP) headers",
                "Sanitize user input using a whitelist approach"
            ])

        if "General Vulnerability" in categories:
            recs.extend([
                "Validate and sanitize all file paths — prevent directory traversal",
                "Never pass user input directly to system commands",
                "Implement proper input validation at every entry point"
            ])

        if not recs:
            recs.append("No vulnerabilities detected — input appears safe")

        return recs


# === ENTRY POINT FOR CHAQUOPY ===
_engine = None

def get_engine():
    global _engine
    if _engine is None:
        _engine = CyberSecurityEngine()
    return _engine

def analyze_security(input_text):
    """Entry point: analyze input for security vulnerabilities."""
    engine = get_engine()
    return engine.analyze(input_text)
