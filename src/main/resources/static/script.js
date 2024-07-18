document.addEventListener('DOMContentLoaded', function () {
    const endpoint = '/weight';
    const dataList = document.getElementById('weightList');

    fetch(endpoint)
        .then(response => response.json())
        .then(data => {
            data.forEach(item => {
                const li = document.createElement('li');
                li.textContent = `${item.id}: Weight: ${item.weightInKilograms}: Date: ${item.date}`;
                dataList.appendChild(li);
            });
        })
        .catch(error => {
            console.error('Error fetching data:', error);
            const li = document.createElement('li');
            li.textContent = 'Failed to fetch data.';
            dataList.appendChild(li);
        });
});