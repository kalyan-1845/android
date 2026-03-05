import { spawn } from 'child_process';
import path from 'path';

const ENGINES_PATH = path.join(process.cwd(), 'lib', 'engines');
const RUNNER_PATH = path.join(ENGINES_PATH, 'run.py');

export async function runEngine(engine: string, func: string, input: string): Promise<any> {
    return new Promise((resolve, reject) => {
        const pythonProcess = spawn('python', [RUNNER_PATH, engine, func, input], {
            env: { ...process.env, PYTHONPATH: ENGINES_PATH }
        });

        let stdout = '';
        let stderr = '';

        pythonProcess.stdout.on('data', (data) => {
            stdout += data.toString();
        });

        pythonProcess.stderr.on('data', (data) => {
            stderr += data.toString();
        });

        pythonProcess.on('close', (code) => {
            if (code !== 0) {
                reject(new Error(`Python process exited with code ${code}: ${stderr}`));
                return;
            }
            try {
                resolve(JSON.parse(stdout));
            } catch (e) {
                reject(new Error(`Failed to parse Python output: ${stdout}`));
            }
        });
    });
}

export async function classifyIntent(input: string) {
    return runEngine('intent_classifier', 'classify_input', input);
}

export async function analyzeModule(module: string, input: string) {
    const mapping: Record<string, { engine: string, func: string }> = {
        'coding': { engine: 'coding_engine', func: 'analyze_code' },
        'cybersecurity': { engine: 'cyber_engine', func: 'analyze_security' },
        'resume': { engine: 'resume_engine', func: 'analyze_resume' },
        'startup': { engine: 'startup_engine', func: 'analyze_startup' }
    };

    const engineMeta = mapping[module];
    if (!engineMeta) throw new Error(`Unknown module: ${module}`);

    return runEngine(engineMeta.engine, engineMeta.func, input);
}
