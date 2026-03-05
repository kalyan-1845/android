"use client";

import { useState } from "react";

export default function Home() {
  const [inputText, setInputText] = useState("");
  const [isProcessing, setIsProcessing] = useState(false);
  const [result, setResult] = useState<any>(null);
  const [error, setError] = useState<string | null>(null);

  const handleWidgetClick = (prefix: string) => {
    setInputText(prefix);
    const inputElement = document.getElementById("command-input");
    if (inputElement) inputElement.focus();
  };

  const executeAnalysis = async () => {
    if (!inputText.trim()) return;

    setIsProcessing(true);
    setError(null);
    setResult(null);

    try {
      const response = await fetch("/api/analyze", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ input: inputText }),
      });

      const data = await response.json();
      if (!response.ok) throw new Error(data.error || "Analysis failed");

      setResult(data);
    } catch (err: any) {
      setError(err.message);
    } finally {
      setIsProcessing(false);
    }
  };

  return (
    <main>
      {/* Header */}
      <header className="fade-in">
        <h1 className="gradient-text">OMNIAGENT</h1>
        <p style={{ color: "var(--text-secondary)", fontSize: "0.9rem" }}>
          Unrestricted Professional Business Suite
        </p>
      </header>

      {/* Command Panel */}
      <section className="glass fade-in" style={{ padding: "1.5rem", marginTop: "2rem" }}>
        <div style={{ display: "flex", gap: "1rem", alignItems: "center" }}>
          <div style={{ color: "var(--primary)", fontWeight: "bold" }}>$</div>
          <input
            id="command-input"
            type="text"
            value={inputText}
            disabled={isProcessing}
            onChange={(e) => setInputText(e.target.value)}
            onKeyDown={(e) => e.key === "Enter" && executeAnalysis()}
            placeholder="Enter command or select a module below..."
            style={{
              flex: 1,
              background: "transparent",
              border: "none",
              color: "var(--text-primary)",
              fontSize: "1.1rem",
              outline: "none"
            }}
          />
          <button
            onClick={executeAnalysis}
            disabled={isProcessing}
            style={{
              padding: "0.5rem 1.5rem",
              borderRadius: "0.5rem",
              background: isProcessing ? "var(--text-tertiary)" : "var(--primary)",
              color: "white",
              border: "none",
              fontWeight: "bold",
              cursor: isProcessing ? "default" : "pointer",
              transition: "transform 0.1s"
            }}
          >
            {isProcessing ? "PROCESSING..." : "EXECUTE"}
          </button>
        </div>
      </section>

      {/* Error Banner */}
      {error && (
        <div style={{ marginTop: "1rem", color: "var(--danger)", fontSize: "0.9rem", padding: "1rem", borderRadius: "0.5rem", background: "rgba(255, 82, 82, 0.1)" }}>
          {error}
        </div>
      )}

      {/* Power Grid */}
      <section className="card-grid fade-in" style={{ animationDelay: "0.1s" }}>
        <ModuleCard
          title="BUSINESS"
          desc="Startup Feasibility & SWOT"
          color="var(--success)"
          onClick={() => handleWidgetClick("Analyze startup: ")}
        />
        <ModuleCard
          title="CODING"
          desc="Logic & Bug Analysis"
          color="var(--primary)"
          onClick={() => handleWidgetClick("Analyze code: ")}
        />
        <ModuleCard
          title="CYBERSEC"
          desc="Vulnerability Scan"
          color="var(--danger)"
          onClick={() => handleWidgetClick("Security scan: ")}
        />
        <ModuleCard
          title="RESUME"
          desc="ATS & Career Scout"
          color="var(--accent)"
          onClick={() => handleWidgetClick("Score resume: ")}
        />
      </section>

      {/* Analysis Result */}
      <section className="fade-in" style={{ flex: 1, marginTop: "2rem" }}>
        {result ? (
          <div className="glass" style={{ padding: "2rem" }}>
            <h2 style={{ color: "var(--primary)", marginBottom: "1rem" }}>
              {result.classification.module_name}
            </h2>

            <div style={{ display: "grid", gridTemplateColumns: "1fr 1fr", gap: "2rem" }}>
              <div>
                <h4 style={{ color: "var(--text-secondary)", marginBottom: "1rem" }}>Reasoning Chain</h4>
                {result.classification.reasoning.map((step: string, i: number) => (
                  <div key={i} style={{ display: "flex", gap: "0.5rem", fontSize: "0.85rem", marginBottom: "0.5rem", color: "var(--text-secondary)" }}>
                    <span style={{ color: "var(--primary)" }}>•</span> {step}
                  </div>
                ))}
              </div>

              {result.engine_result && (
                <div>
                  <h4 style={{ color: "var(--text-secondary)", marginBottom: "1rem" }}>Deep Analysis</h4>
                  <pre style={{
                    background: "rgba(0,0,0,0.2)",
                    padding: "1rem",
                    borderRadius: "0.5rem",
                    fontSize: "0.8rem",
                    overflowX: "auto",
                    maxHeight: "400px"
                  }}>
                    {JSON.stringify(result.engine_result.structured_analysis, null, 2)}
                  </pre>
                </div>
              )}
            </div>
          </div>
        ) : (
          <div className="glass" style={{ height: "100%", padding: "2rem", display: "flex", justifyContent: "center", alignItems: "center", color: "var(--text-tertiary)" }}>
            {isProcessing ? "Performing kernel-level analysis..." : "Select a module to begin deep forensic analysis"}
          </div>
        )}
      </section>
    </main>
  );
}

function ModuleCard({ title, desc, color, onClick }: { title: string, desc: string, color: string, onClick: () => void }) {
  return (
    <div
      className="glass"
      onClick={onClick}
      style={{
        padding: "1.5rem",
        cursor: "pointer",
        transition: "all 0.2s ease",
        borderLeft: `4px solid ${color}`
      }}
      onMouseEnter={(e) => {
        e.currentTarget.style.transform = "translateY(-4px)";
        e.currentTarget.style.borderColor = color;
        e.currentTarget.style.background = "var(--surface-hover)";
      }}
      onMouseLeave={(e) => {
        e.currentTarget.style.transform = "translateY(0)";
        e.currentTarget.style.background = "var(--glass)";
      }}
    >
      <h3 style={{ color, marginBottom: "0.5rem" }}>{title}</h3>
      <p style={{ color: "var(--text-secondary)", fontSize: "0.85rem" }}>{desc}</p>
    </div>
  );
}
