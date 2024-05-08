// Obtener los botones por su id
const getGroupsBtn = document.getElementById('getGroupsBtn');
const getPackagesByGroupBtn = document.getElementById('getPackagesByGroupBtn');
const packageDetailsBtn = document.getElementById('getPackageDetailsBtn');

// Agregar event listener al botón
packageDetailsBtn.addEventListener('click', () => {
    const packageName = prompt("Enter package name:");
    if (packageName) {
        getPackageDetails(packageName);
    }
});
// Agregar event listener a cada botón
getGroupsBtn.addEventListener('click', getGroups);
getPackagesByGroupBtn.addEventListener('click', getPackagesByGroup);

function handleResponse(response) {
    const responseDiv = document.getElementById('response');
    responseDiv.innerHTML = `<pre>${response}</pre>`;
}

function getGroups() {
    fetch('http://localhost:8080/api/v1/native/groups')
        .then(response => response.json())
        .then(data => handleGetGroupsResponse(data))
        .catch(error => console.error('Error:', error));
}

function getPackagesByGroup() {
    const groupName = prompt("Enter group name:");
    if (groupName) {
        fetch(`http://localhost:8080/api/v1/native/groups/${groupName}`)
            .then(response => {
                if (response.status === 404) {
                    return response.text().then(errorMessage => {
                        const errorJson = JSON.parse(errorMessage);
                        const errorMessageHTML = `Error: ${errorJson.message}`;
                        const responseDiv = document.getElementById('response');
                        responseDiv.innerHTML = errorMessageHTML;
                    });
                } else {
                    return response.json();
                }
            })
            .then(data => handlePackagesByGroupResponse(data))
            .catch(error => console.error('Error:', error));
    }
}

function handleGetGroupsResponse(response) {
    const groupNames = response.groupNames;
    const responseDiv = document.getElementById('response');
    const html = generateGroupList(groupNames);
    responseDiv.innerHTML = `<pre>${html}</pre>`;
}

function generateGroupList(groupNames) {
    let listHTML = "<h3>PacMan Groups:</h3><ul>";
    groupNames.forEach(groupName => {
        listHTML += `<li>${groupName}</li>`;
    });
    listHTML += "</ul>";
    return listHTML;
}

function handlePackagesByGroupResponse(response) {
    const packages = response.packages;
    const responseDiv = document.getElementById('response');
    const html = generatePackageList(packages);
    responseDiv.innerHTML = `<pre>${html}</pre>`;
}

function generatePackageList(packages) {
    let listHTML = "<h3>Packages in Group:</h3><ul>";
    packages.forEach(package => {
        listHTML += `<li>${package}</li>`;
    });
    listHTML += "</ul>";
    return listHTML;
}



function getPackageDetails(packageName) {
    fetch(`http://localhost:8080/api/v1/native/groups/package/${packageName}`)
        .then(response => response.json())
        .then(data => handlePackageDetailsResponse(data))
        .catch(error => console.error('Error:', error));
}

function handlePackageDetailsResponse(response) {
    const responseDiv = document.getElementById('response');
    const packageData = response;
    const tableHTML = generatePackageDetailsTable(packageData);
    responseDiv.innerHTML = `<pre>${tableHTML}</pre>`;;
}

function generatePackageDetailsTable(packageData) {
    let tableHTML = '<table border="1">';
    tableHTML += '<tbody>';

    tableHTML += generateRow('Repository', packageData.repository);
    tableHTML += generateRow('Name', packageData.name);
    tableHTML += generateRow('Version', packageData.version);
    tableHTML += generateRow('Description', packageData.description);
    tableHTML += generateRow('Architecture', packageData.architecture);
    tableHTML += generateRow('URL', packageData.url);
    tableHTML += generateRow('Groups', packageData.groups.join(', '));
    tableHTML += generateRow('Provides', packageData.provides.join(', '));
    tableHTML += generateRow('Depends', packageData.depends.join(', '));
    tableHTML += generateRow('Optional Dependencies', packageData.optionalDependencies.join(', '));
    tableHTML += generateRow('In Conflict With', packageData.inConflictWith.join(', '));
    tableHTML += generateRow('Replaces', packageData.replaces);
    tableHTML += generateRow('Download Size', `${packageData.downloadSize.value} ${packageData.downloadSize.unit}`);
    tableHTML += generateRow('Installation Size', `${packageData.installationSize.value} ${packageData.installationSize.unit}`);
    tableHTML += generateRow('Manager', packageData.manager);
    tableHTML += generateRow('Creation Date', packageData.creationDate);
    tableHTML += generateRow('Validated By', packageData.validatedBy);

    tableHTML += '</tbody>';
    tableHTML += '</table>';

    return tableHTML;
}

function generateRow(label, value) {
    return `<tr><td><strong>${label}:</strong></td><td>${value}</td></tr>`;
}
