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

    tableHTML += generateRow('Name', package.name);
    tableHTML += generateRow('Version', package.version);
    tableHTML += generateRow('Description', package.description);
    tableHTML += generateRow('Architecture', package.architecture);
    tableHTML += generateRow('URL', package.url);
    tableHTML += generateRow('Licences', package.licences);
    tableHTML += generateRow('Groups', package.groups.join(', '));
    tableHTML += generateRow('Provides', package.provides.join(', '));
    tableHTML += generateRow('Depends', package.depends.join(', '));
    tableHTML += generateRow('Optional Dependencies', package.optionalDependencies.join('<br>'));
    tableHTML += generateRow('Requested By', package.requestedBy.join(', '));
    tableHTML += generateRow('Optional For', package.optionalFor.join(', '));
    tableHTML += generateRow('In Conflict With', package.inConflictWith.join(', '));
    tableHTML += generateRow('Replaces', package.replaces.join(', '));
    tableHTML += generateRow('Size', `${package.size.value} ${package.size.unit}`);
    tableHTML += generateRow('Manager', package.manager);
    tableHTML += generateRow('Creation Date Time', package.creationDateTime);
    tableHTML += generateRow('Install Date Time', package.installDateTime);
    tableHTML += generateRow('Reason Installation', package.reasonInstallation);
    tableHTML += generateRow('Installation Script', package.installationScript);
    tableHTML += generateRow('Validated By', package.validatedBy);

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

// Obtener el botón por su id
const packageDetailsBtn = document.getElementById('getPackageDetailsBtn');

// Agregar event listener al botón
packageDetailsBtn.addEventListener('click', () => {
    const packageName = prompt("Enter package name:");
    if (packageName) {
        getPackageDetails(packageName);
    }
});

function getPackageDetails(packageName) {
    fetch(`http://localhost:8080/api/v1/pacman/groups/package/${packageName}`)
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
