const { spawn } = require('child_process');
const path = require('path');

const ENGINES_PATH = path.join(process.cwd(), 'lib', 'engines');
const RUNNER_PATH = path.join(ENGINES_PATH, 'run.py');

function runEngine(engine, func, input) {
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
                reject(new Error(`Failed to parse Python output: ${e.message}\nOutput was: ${stdout}`));
            }
        });
    });
}

async function test() {
    try {
        console.log("Testing intent_classifier...");
        let res = await runEngine('intent_classifier', 'classify_input', 'Analyze code: print("hello")');
        console.log("Intent output parsed successfully:", typeof res);

        console.log("Testing coding_engine...");
        res = await runEngine('coding_engine', 'analyze_code', 'Analyze code: print("hello")');
        console.log("Coding output parsed successfully:", typeof res);

        console.log("ALL TESTS PASSED.");
    } catch (e) {
        console.error(e);
    }
}
test();
