package com.omniagent.app.data.model

/**
 * Represents the classification result from the AI Kernel.
 */
data class ClassificationResult(
    val status: String = "",
    val module: String? = null,
    val moduleName: String = "",
    val confidence: Double = 0.0,
    val confidenceLevel: String = "",
    val allScores: Map<String, Double> = emptyMap(),
    val ranking: List<ModuleScore> = emptyList(),
    val reasoning: List<String> = emptyList(),
    val inputFeatures: Int = 0,
    val timestamp: String = ""
)

data class ModuleScore(
    val module: String = "",
    val score: Double = 0.0
)

/**
 * Represents the result from any domain engine.
 */
data class EngineResult(
    val engine: String = "",
    val timestamp: String = "",
    val status: String = "",
    val rawJson: String = "",
    val summary: Map<String, Any> = emptyMap()
)

/**
 * Represents the full analysis pipeline result.
 */
data class AnalysisPipelineResult(
    val classification: ClassificationResult,
    val engineResult: EngineResult?,
    val totalProcessingTimeMs: Long = 0
)

/**
 * Represents user roles for RBAC.
 */
enum class UserRole(val displayName: String) {
    ADMIN("Administrator"),
    USER("Standard User");

    companion object {
        fun fromString(value: String): UserRole {
            return when (value.lowercase()) {
                "admin", "administrator" -> ADMIN
                else -> USER
            }
        }
    }
}
