import React, { useState } from 'react';

function DocumentUpload() {
    const [file, setFile] = useState(null);

    const handleUpload = async () => {
        const formData = new FormData();
        formData.append('file', file);
        const response = await fetch('/api/documents/upload', {
            method: 'POST',
            body: formData,
        });
        const message = await response.text();
        alert(message);
    };

    return (
        <div>
            <h3>Upload Document</h3>
            <input type="file" onChange={(e) => setFile(e.target.files[0])} />
            <button onClick={handleUpload}>Upload</button>
        </div>
    );
}

export default DocumentUpload;
