package com.omniagent.app.ui.features.export;

/**
 * PDF Report Exporter — generates structured, branded PDF reports from analysis logs.
 * Uses Android's built-in PdfDocument API (fully open-source, zero dependencies).
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J \u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\tH\u0002J\u0010\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002J2\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00172\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\u0019J \u0010\u001a\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\tJ&\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\t0\u00172\u0006\u0010\u001c\u001a\u00020\t2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006 "}, d2 = {"Lcom/omniagent/app/ui/features/export/PdfReportExporter;", "", "()V", "MARGIN", "", "PAGE_HEIGHT", "", "PAGE_WIDTH", "WATERMARK_TEXT", "", "drawReport", "", "canvas", "Landroid/graphics/Canvas;", "log", "Lcom/omniagent/app/core/model/AnalysisLog;", "decryptedResult", "drawWatermark", "exportAllLogs", "Ljava/io/File;", "context", "Landroid/content/Context;", "logs", "", "decryptor", "Lkotlin/Function1;", "exportReport", "wrapText", "text", "paint", "Landroid/graphics/Paint;", "maxWidth", "app_debug"})
public final class PdfReportExporter {
    private static final int PAGE_WIDTH = 595;
    private static final int PAGE_HEIGHT = 842;
    private static final float MARGIN = 40.0F;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String WATERMARK_TEXT = "OmniAgent AI \u2022 Confidential";
    @org.jetbrains.annotations.NotNull()
    public static final com.omniagent.app.ui.features.export.PdfReportExporter INSTANCE = null;
    
    private PdfReportExporter() {
        super();
    }
    
    /**
     * Export a single analysis log as a professional PDF.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.io.File exportReport(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.omniagent.app.core.model.AnalysisLog log, @org.jetbrains.annotations.NotNull()
    java.lang.String decryptedResult) {
        return null;
    }
    
    /**
     * Export all logs as a consolidated PDF.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.io.File exportAllLogs(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.util.List<com.omniagent.app.core.model.AnalysisLog> logs, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, java.lang.String> decryptor) {
        return null;
    }
    
    private final void drawReport(android.graphics.Canvas canvas, com.omniagent.app.core.model.AnalysisLog log, java.lang.String decryptedResult) {
    }
    
    private final void drawWatermark(android.graphics.Canvas canvas) {
    }
    
    private final java.util.List<java.lang.String> wrapText(java.lang.String text, android.graphics.Paint paint, float maxWidth) {
        return null;
    }
}