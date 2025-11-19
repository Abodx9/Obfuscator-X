     
	 async function Obfuscatex() {
            const inputText = document.getElementById('inputText').value;
            const lang_endp = document.getElementById('ChosenProg').value;
            const response = await fetch(lang_endp, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: inputText
            });

            if (response.ok) {
                const resultText = await response.text();
                document.getElementById('resultText').value = resultText;
            } else {
                document.getElementById('resultText').value = 'Error calling server';
            }
        }

async function pasteToInput() {
            try {
                const text = await navigator.clipboard.readText();
                document.getElementById('inputText').value = text;
            } catch (err) {
                alert('Failed permission.');
            }
}

async function copyFromOutput() {
            const resultText = document.getElementById('resultText');
            if (resultText.value.trim() === '') {
                alert('add some add some code firest!');
                return;
            }
            
            try {
                await navigator.clipboard.writeText(resultText.value);
                const copyBtn = document.querySelector('.action-button.copy');
                const originalText = copyBtn.textContent;
                copyBtn.textContent = 'Copied!';
                copyBtn.style.background = 'linear-gradient(135deg, #4CAF50 0%, #45a049 100%)';
                
                setTimeout(() => {
                    copyBtn.textContent = originalText;
                    copyBtn.style.background = 'linear-gradient(135deg, #667eea 0%, #764ba2 100%)';
                }, 2000);
            } catch (err) {
                alert('Failed permission.');
            }
}