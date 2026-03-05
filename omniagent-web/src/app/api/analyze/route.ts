import { NextResponse } from 'next/server';
import { classifyIntent, analyzeModule } from '@/../lib/engines/bridge';

export async function POST(request: Request) {
    try {
        const { input } = await request.json();

        if (!input) {
            return NextResponse.json({ error: 'No input provided' }, { status: 400 });
        }

        // 1. Classify Intent
        const classification = await classifyIntent(input);

        if (classification.status === 'error') {
            return NextResponse.json(classification, { status: 500 });
        }

        // 2. Route to specific engine if module is detected
        let analysisResult = null;
        if (classification.module) {
            analysisResult = await analyzeModule(classification.module, input);
        }

        // 3. Combine results
        return NextResponse.json({
            classification,
            engine_result: analysisResult,
            timestamp: new Date().toISOString()
        });

    } catch (error: any) {
        console.error('Analysis error:', error);
        return NextResponse.json({
            error: 'System error during analysis',
            details: error.message
        }, { status: 500 });
    }
}
