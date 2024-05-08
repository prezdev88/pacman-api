// Obtener los botones por su id
const installedPackagesBtn = document.getElementById('getInstalledPackagesBtn');
const liteInstalledPackagesBtn = document.getElementById('getLiteInstalledPackagesBtn');
const packagesToUpgradeBtn = document.getElementById('getPackagesToUpgradeBtn');
const installedPackageByNameBtn = document.getElementById('getInstalledPackageByNameBtn');

// Agregar event listener a cada botón
installedPackagesBtn.addEventListener('click', getInstalledPackages);
liteInstalledPackagesBtn.addEventListener('click', getLiteInstalledPackages);
packagesToUpgradeBtn.addEventListener('click', getPackagesToUpgrade);
installedPackageByNameBtn.addEventListener('click', getInstalledPackageByName);

function handleResponse(response) {
    const responseDiv = document.getElementById('response');
    responseDiv.innerHTML = `<pre>${response}</pre>`;
}

function getInstalledPackages() {
    fetch('http://localhost:8080/api/v1/pacman/packages/installed/explicit')
        .then(response => response.text())
        .then(data => handleGetInstalledPackagesResponse(data))
        .catch(error => console.error('Error:', error));
}

function getLiteInstalledPackages() {
    fetch('http://localhost:8080/api/v1/pacman/packages/installed/explicit/lite')
        .then(response => response.text())
        .then(data => handleLiteExplicitInstalledPackagesResponse(data))
        .catch(error => console.error('Error:', error));
}

function getPackagesToUpgrade() {
    const rootPassword = prompt("Enter root password:");
    if (!rootPassword) {
        return;
    }

    fetch(`http://localhost:8080/api/v1/pacman/packages/upgrade?password=${rootPassword}`)
        .then(response => {
            if (response.status === 400) {
                return response.text().then(error => {
                    const errorMessageHTML = "Error: Wrong password";
                    const responseDiv = document.getElementById('response');
                    responseDiv.innerHTML = errorMessageHTML;
                    throw new Error(errorMessageHTML);
                });
            } else if (response.status === 204) {
                const errorMessageHTML = "Info: No packages to upgrade";
                const responseDiv = document.getElementById('response');
                responseDiv.innerHTML = errorMessageHTML;
                throw new Error(errorMessageHTML);
            } else {
                return response.text();
            }
        })
        .then(data => handleUpgradePackagesResponse(data))
        .catch(error => console.error('Error:', error));
}

function getInstalledPackageByName() {
    const packageName = prompt("Enter package name:");
    if (packageName) {
        fetch(`http://localhost:8080/api/v1/pacman/packages/${packageName}`)
            .then(response => {
                if (response.status === 404) {
                    return response.text().then(errorMessage => {
                        const errorJson = JSON.parse(errorMessage);

                        // Generar mensaje de error en HTML dentro de una tabla
                        const errorMessageHTML = `
                            Error: ${errorJson.message}
                        `;
                        const responseDiv = document.getElementById('response');
                        responseDiv.innerHTML = errorMessageHTML;
                    });
                } else {
                    return response.text();
                }
            })
            .then(data => handlePackageByNameResponse(data))
            .catch(error => console.error('Error:', error));
    }
}

function handleGetInstalledPackagesResponse(response) {
    const jsonData = JSON.parse(response);
    const responseDiv = document.getElementById('response');
    const html = generatePackagesTable(jsonData);
    responseDiv.innerHTML = `<pre>${html}</pre>`;
}

function generatePackagesTable(jsonData) {
    // Obtener la lista de paquetes del JSON
    const packages = jsonData.packages;

    const packageCount = packages.length;

    // Crear una tabla HTML
    let tableHTML = `Total Packages: ${packageCount}<table border="1">`;
    tableHTML += '<thead>';
    tableHTML += '<tr>';
    tableHTML += '<th>Name</th>';
    tableHTML += '<th>Version</th>';
    tableHTML += '<th>Description</th>';
    tableHTML += '<th>Architecture</th>';
    tableHTML += '<th>URL</th>';
    tableHTML += '<th>Licences</th>';
    tableHTML += '<th>Groups</th>';
    tableHTML += '<th>Provides</th>';
    tableHTML += '<th>Depends</th>';
    tableHTML += '<th>Optional Dependencies</th>';
    tableHTML += '<th>Requested By</th>';
    tableHTML += '<th>Optional For</th>';
    tableHTML += '<th>In Conflict With</th>';
    tableHTML += '<th>Replaces</th>';
    tableHTML += '<th>Size</th>';
    tableHTML += '<th>Manager</th>';
    tableHTML += '<th>Creation Date Time</th>';
    tableHTML += '<th>Install Date Time</th>';
    tableHTML += '<th>Reason Installation</th>';
    tableHTML += '<th>Installation Script</th>';
    tableHTML += '<th>Validated By</th>';
    tableHTML += '</tr>';
    tableHTML += '</thead>';
    tableHTML += '<tbody>';

    // Iterar sobre cada paquete y agregar una fila a la tabla por cada uno
    packages.forEach(package => {
        tableHTML += '<tr>';
        tableHTML += `<td>${package.name}</td>`;
        tableHTML += `<td>${package.version}</td>`;
        tableHTML += `<td>${package.description}</td>`;
        tableHTML += `<td>${package.architecture}</td>`;
        tableHTML += `<td><a href="${package.url}" target="_blank">${package.url}</a></td>`;
        tableHTML += `<td>${package.licences}</td>`;
        tableHTML += `<td>${package.groups.join(', ')}</td>`;
        tableHTML += `<td>${package.provides.join(', ')}</td>`;
        tableHTML += `<td>${package.depends.join(', ')}</td>`;
        tableHTML += `<td>${package.optionalDependencies.join(', ')}</td>`;
        tableHTML += `<td>${package.requestedBy.join(', ')}</td>`;
        tableHTML += `<td>${package.optionalFor.join(', ')}</td>`;
        tableHTML += `<td>${package.inConflictWith.join(', ')}</td>`;
        tableHTML += `<td>${package.replaces.join(', ')}</td>`;
        tableHTML += `<td>${package.size.value} ${package.size.unit}</td>`;
        tableHTML += `<td>${package.manager}</td>`;
        tableHTML += `<td>${package.creationDateTime}</td>`;
        tableHTML += `<td>${package.installDateTime}</td>`;
        tableHTML += `<td>${package.reasonInstallation}</td>`;
        tableHTML += `<td>${package.installationScript}</td>`;
        tableHTML += `<td>${package.validatedBy}</td>`;
        tableHTML += '</tr>';
    });

    tableHTML += '</tbody>';
    tableHTML += '</table>';

    return tableHTML;
}

function handleLiteExplicitInstalledPackagesResponse(response) {
    const jsonData = JSON.parse(response);
    const responseDiv = document.getElementById('response');
    const html = generateLitePackageTable(jsonData);
    responseDiv.innerHTML = `<pre>${html}</pre>`;
}

function generateLitePackageTable(jsonData) {
    const packages = jsonData.packages;
    const packageCount = packages.length;

    // Crear una tabla HTML
    let tableHTML = `Total Packages: ${packageCount}<table border="1">`;
    tableHTML += '<thead>';
    tableHTML += '<tr>';
    tableHTML += '<th>Name</th>';
    tableHTML += '<th>Version</th>';
    tableHTML += '</tr>';
    tableHTML += '</thead>';
    tableHTML += '<tbody>';

    packages.forEach(package => {
        tableHTML += '<tr>';
        tableHTML += `<td>${package.name}</td>`;
        tableHTML += `<td>${package.version}</td>`;
        tableHTML += '</tr>';
    });

    tableHTML += '</tbody>';
    tableHTML += '</table>';

    return tableHTML;
}

function handleUpgradePackagesResponse(response) {
    const jsonData = JSON.parse(response);
    const responseDiv = document.getElementById('response');
    const html = generateUpgradePackageTable(jsonData);
    responseDiv.innerHTML = `<pre>${html}</pre>`;
}

function generateUpgradePackageTable(jsonData) {
    const packages = jsonData.packages;
    const packageCount = packages.length;

    // Crear una tabla HTML
    let tableHTML = `Total Packages: ${packageCount}<table border="1">`;
    tableHTML += '<thead>';
    tableHTML += '<tr>';
    tableHTML += '<th>Name</th>';
    tableHTML += '<th>Version</th>';
    tableHTML += '<th>New Version</th>';
    tableHTML += '</tr>';
    tableHTML += '</thead>';
    tableHTML += '<tbody>';

    packages.forEach(package => {
        tableHTML += '<tr>';
        tableHTML += `<td>${package.name}</td>`;
        tableHTML += `<td>${package.version}</td>`;
        tableHTML += `<td>${package.newVersion}</td>`;
        tableHTML += '</tr>';
    });

    tableHTML += '</tbody>';
    tableHTML += '</table>';

    return tableHTML;
}

function handlePackageByNameResponse(response) {
    const jsonData = JSON.parse(response);
    const responseDiv = document.getElementById('response');
    const html = generatePackageTable(jsonData.package);
    responseDiv.innerHTML = `<pre>${html}</pre>`;
}

function generatePackageTable(package) {
    let tableHTML = '<table border="1">';
    tableHTML += '<tbody>';

    tableHTML += '<tr>';
    tableHTML += `<td><strong>Name:</strong></td>`;
    tableHTML += `<td>${package.name}</td>`;
    tableHTML += '</tr>';

    tableHTML += '<tr>';
    tableHTML += `<td><strong>Version:</strong></td>`;
    tableHTML += `<td>${package.version}</td>`;
    tableHTML += '</tr>';

    tableHTML += '<tr>';
    tableHTML += `<td><strong>Description:</strong></td>`;
    tableHTML += `<td>${package.description}</td>`;
    tableHTML += '</tr>';

    tableHTML += '<tr>';
    tableHTML += `<td><strong>Architecture:</strong></td>`;
    tableHTML += `<td>${package.architecture}</td>`;
    tableHTML += '</tr>';

    tableHTML += '<tr>';
    tableHTML += `<td><strong>URL:</strong></td>`;
    tableHTML += `<td>${package.url}</td>`;
    tableHTML += '</tr>';

    tableHTML += '<tr>';
    tableHTML += `<td><strong>Licences:</strong></td>`;
    tableHTML += `<td>${package.licences}</td>`;
    tableHTML += '</tr>';

    tableHTML += '<tr>';
    tableHTML += `<td><strong>Groups:</strong></td>`;
    tableHTML += `<td>${package.groups.join(', ')}</td>`;
    tableHTML += '</tr>';

    tableHTML += '<tr>';
    tableHTML += `<td><strong>Provides:</strong></td>`;
    tableHTML += `<td>${package.provides.join(', ')}</td>`;
    tableHTML += '</tr>';

    tableHTML += '<tr>';
    tableHTML += `<td><strong>Depends:</strong></td>`;
    tableHTML += `<td>${package.depends.join(', ')}</td>`;
    tableHTML += '</tr>';

    tableHTML += '<tr>';
    tableHTML += `<td><strong>Optional Dependencies:</strong></td>`;
    tableHTML += `<td>${package.optionalDependencies.join('<br>')}</td>`;
    tableHTML += '</tr>';

    tableHTML += '<tr>';
    tableHTML += `<td><strong>Requested By:</strong></td>`;
    tableHTML += `<td>${package.requestedBy.join(', ')}</td>`;
    tableHTML += '</tr>';

    tableHTML += '<tr>';
    tableHTML += `<td><strong>Optional For:</strong></td>`;
    tableHTML += `<td>${package.optionalFor.join(', ')}</td>`;
    tableHTML += '</tr>';

    tableHTML += '<tr>';
    tableHTML += `<td><strong>In Conflict With:</strong></td>`;
    tableHTML += `<td>${package.inConflictWith.join(', ')}</td>`;
    tableHTML += '</tr>';

    tableHTML += '<tr>';
    tableHTML += `<td><strong>Replaces:</strong></td>`;
    tableHTML += `<td>${package.replaces.join(', ')}</td>`;
    tableHTML += '</tr>';

    tableHTML += '<tr>';
    tableHTML += `<td><strong>Size:</strong></td>`;
    tableHTML += `<td>${package.size.value} ${package.size.unit}</td>`;
    tableHTML += '</tr>';

    tableHTML += '<tr>';
    tableHTML += `<td><strong>Manager:</strong></td>`;
    tableHTML += `<td>${package.manager}</td>`;
    tableHTML += '</tr>';

    tableHTML += '<tr>';
    tableHTML += `<td><strong>Creation Date Time:</strong></td>`;
    tableHTML += `<td>${package.creationDateTime}</td>`;
    tableHTML += '</tr>';

    tableHTML += '<tr>';
    tableHTML += `<td><strong>Install Date Time:</strong></td>`;
    tableHTML += `<td>${package.installDateTime}</td>`;
    tableHTML += '</tr>';

    tableHTML += '<tr>';
    tableHTML += `<td><strong>Reason Installation:</strong></td>`;
    tableHTML += `<td>${package.reasonInstallation}</td>`;
    tableHTML += '</tr>';

    tableHTML += '<tr>';
    tableHTML += `<td><strong>Installation Script:</strong></td>`;
    tableHTML += `<td>${package.installationScript}</td>`;
    tableHTML += '</tr>';

    tableHTML += '<tr>';
    tableHTML += `<td><strong>Validated By:</strong></td>`;
    tableHTML += `<td>${package.validatedBy}</td>`;
    tableHTML += '</tr>';

    tableHTML += '</tbody>';
    tableHTML += '</table>';

    return tableHTML;
}

// Obtener los botones por su id
const getGroupsBtn = document.getElementById('getGroupsBtn');
const getPackagesByGroupBtn = document.getElementById('getPackagesByGroupBtn');

// Agregar event listener a cada botón
getGroupsBtn.addEventListener('click', getGroups);
getPackagesByGroupBtn.addEventListener('click', getPackagesByGroup);

function handleResponse(response) {
    const responseDiv = document.getElementById('response');
    responseDiv.innerHTML = `<pre>${response}</pre>`;
}

function getGroups() {
    fetch('http://localhost:8080/api/v1/pacman/groups')
        .then(response => response.json())
        .then(data => handleGetGroupsResponse(data))
        .catch(error => console.error('Error:', error));
}

function getPackagesByGroup() {
    const groupName = prompt("Enter group name:");
    if (groupName) {
        fetch(`http://localhost:8080/api/v1/pacman/groups/${groupName}`)
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
