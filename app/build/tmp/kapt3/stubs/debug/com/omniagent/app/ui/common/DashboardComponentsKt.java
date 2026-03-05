package com.omniagent.app.ui.common;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\b\u001aD\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0007\u001ak\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\n\u001a\u00020\u000b2\u0015\b\u0002\u0010\u0012\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0007\u00a2\u0006\u0002\b\u00132\u001c\u0010\u0014\u001a\u0018\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00010\u0005\u00a2\u0006\u0002\b\u0013\u00a2\u0006\u0002\b\u0016H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0017\u0010\u0018\u001a.\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u001a\u001a\u00020\u001b2\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0007\u001a&\u0010\u001d\u001a\u00020\u00012\u0012\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0007\u001a \u0010\u001f\u001a\u00020\u00012\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00010\u00072\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0007\u001a6\u0010!\u001a\u00020\u00012\u0006\u0010\"\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010#\u001a\u00020\u00112\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b$\u0010%\u001aJ\u0010&\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u00032\u0006\u0010\'\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010#\u001a\u00020\u00112\b\b\u0002\u0010\n\u001a\u00020\u000b2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00010\u0007H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b(\u0010)\u001a:\u0010*\u001a\u00020\u00012\u0006\u0010+\u001a\u00020\u00032\u0006\u0010,\u001a\u00020\u00032\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020\u00032\u0006\u00100\u001a\u0002012\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0007\u001a@\u00102\u001a\u00020\u00012\u0006\u0010\"\u001a\u00020\u00032\u0006\u00103\u001a\u0002042\b\b\u0002\u00105\u001a\u0002042\b\b\u0002\u0010#\u001a\u00020\u00112\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b6\u00107\u001a,\u00108\u001a\u00020\u00012\u0006\u00109\u001a\u00020\u00032\u0006\u0010#\u001a\u00020\u00112\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b:\u0010;\u0082\u0002\u0007\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006<"}, d2 = {"CommandInputPanel", "", "value", "", "onValueChange", "Lkotlin/Function1;", "onSubmit", "Lkotlin/Function0;", "isProcessing", "", "modifier", "Landroidx/compose/ui/Modifier;", "DashboardCard", "title", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "iconColor", "Landroidx/compose/ui/graphics/Color;", "headerAction", "Landroidx/compose/runtime/Composable;", "content", "Landroidx/compose/foundation/layout/ColumnScope;", "Lkotlin/ExtensionFunctionType;", "DashboardCard-FNF3uiM", "(Ljava/lang/String;Landroidx/compose/ui/graphics/vector/ImageVector;JLandroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;)V", "DashboardTabBar", "selectedTab", "", "onTabSelected", "DemoPresentationBar", "onDemoClick", "ExportButton", "onClick", "MetricDisplay", "label", "color", "MetricDisplay-9LQNqLg", "(Ljava/lang/String;Ljava/lang/String;JLandroidx/compose/ui/Modifier;)V", "ModuleActionCard", "description", "ModuleActionCard-Bx497Mc", "(Ljava/lang/String;Ljava/lang/String;Landroidx/compose/ui/graphics/vector/ImageVector;JLandroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function0;)V", "ModuleRoutingIndicator", "moduleName", "moduleKey", "confidence", "", "confidenceLevel", "processingTimeMs", "", "ScoreBar", "score", "", "maxScore", "ScoreBar-42QJj7c", "(Ljava/lang/String;FFJLandroidx/compose/ui/Modifier;)V", "StatusBadge", "text", "StatusBadge-bw27NRU", "(Ljava/lang/String;JLandroidx/compose/ui/Modifier;)V", "app_debug"})
public final class DashboardComponentsKt {
    
    /**
     * Command Input Panel — The primary user input component.
     * Designed to look like a terminal command line.
     */
    @androidx.compose.runtime.Composable()
    public static final void CommandInputPanel(@org.jetbrains.annotations.NotNull()
    java.lang.String value, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onValueChange, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onSubmit, boolean isProcessing, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    /**
     * Module Routing Indicator — Shows which engine was selected.
     */
    @androidx.compose.runtime.Composable()
    public static final void ModuleRoutingIndicator(@org.jetbrains.annotations.NotNull()
    java.lang.String moduleName, @org.jetbrains.annotations.NotNull()
    java.lang.String moduleKey, double confidence, @org.jetbrains.annotations.NotNull()
    java.lang.String confidenceLevel, long processingTimeMs, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    /**
     * Tab Bar — Bottom navigation for the dashboard.
     */
    @androidx.compose.runtime.Composable()
    public static final void DashboardTabBar(int selectedTab, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onTabSelected, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    /**
     * Demo Presentation Bar — One-click trigger for hackathon judging.
     */
    @androidx.compose.runtime.Composable()
    public static final void DemoPresentationBar(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onDemoClick, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    /**
     * Branded Export Button for Reports.
     */
    @androidx.compose.runtime.Composable()
    public static final void ExportButton(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onClick, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
}