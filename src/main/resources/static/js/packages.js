// Define la URL base como constante
const BASE_URL = 'http://localhost:8080/api/v1/';

function handleLiteExplicitInstalledPackagesResponse(response) {
    const jsonData = JSON.parse(response);
    const responseDiv = document.getElementById('response');
    const html = generateLitePackageTable(jsonData);
    responseDiv.innerHTML = `<pre>${html}</pre>`;
}

function handleGetInstalledPackagesResponse(response) {
    const jsonData = JSON.parse(response);
    const responseDiv = document.getElementById('response');
    const html = generatePackagesTable(jsonData);
    responseDiv.innerHTML = `<pre>${html}</pre>`;
}

function handleUpgradePackagesResponse(response) {
    const jsonData = JSON.parse(response);
    const responseDiv = document.getElementById('response');
    const html = generateUpgradePackageTable(jsonData);
    responseDiv.innerHTML = `<pre>${html}</pre>`;
}

function handlePackageByNameResponse(response) {
    const jsonData = JSON.parse(response);
    const responseDiv = document.getElementById('response');
    const html = generatePackageTable(jsonData.package);
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

// Funciones para obtener los paquetes instalados
function getInstalledPackages(type) {
    fetch(`${BASE_URL}${type}/packages/installed/explicit`)
        .then(response => response.text())
        .then(data => handleGetInstalledPackagesResponse(data))
        .catch(error => console.error('Error:', error));
}

// Funciones para obtener paquetes instalados de forma lite
function getLiteInstalledPackages(type) {
    fetch(`${BASE_URL}${type}/packages/installed/explicit/lite`)
        .then(response => response.text())
        .then(data => handleLiteExplicitInstalledPackagesResponse(data))
        .catch(error => console.error('Error:', error));
}

// Funciones para obtener los paquetes para actualizar
function getPackagesToUpgrade(type) {
    const rootPassword = prompt("Enter root password:");
    if (!rootPassword) {
        return;
    }

    fetch(`${BASE_URL}${type}/packages/upgrade?password=${rootPassword}`)
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

// Función para obtener información de un paquete por su nombre
function getInstalledPackageByName(type) {
    const packageName = prompt("Enter package name:");
    if (packageName) {
        fetch(`${BASE_URL}${type}/packages/${packageName}`)
            .then(response => {
                if (response.status === 404) {
                    return response.text().then(errorMessage => {
                        const errorJson = JSON.parse(errorMessage);
                        const errorMessageHTML = `Error: ${errorJson.message}`;
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

// Función para generar una fila de tabla HTML
function generateRow(label, value) {
    return `<tr><td><strong>${label}:</strong></td><td>${value}</td></tr>`;
}

// Obtener los botones por su id
const installedPackagesBtn = document.getElementById('getInstalledPackagesBtn');
const liteInstalledPackagesBtn = document.getElementById('getLiteInstalledPackagesBtn');
const packagesToUpgradeBtn = document.getElementById('getPackagesToUpgradeBtn');
const installedPackageByNameBtn = document.getElementById('getInstalledPackageByNameBtn');

// Agregar event listener a cada botón para paquetes nativos
installedPackagesBtn.addEventListener('click', () => getInstalledPackages('native'));
liteInstalledPackagesBtn.addEventListener('click', () => getLiteInstalledPackages('native'));
packagesToUpgradeBtn.addEventListener('click', () => getPackagesToUpgrade('native'));
installedPackageByNameBtn.addEventListener('click', () => getInstalledPackageByName('native'));

// Obtener los botones por su id para paquetes extranjeros
const installedPackagesBtn2 = document.getElementById('getInstalledPackagesBtn2');
const liteInstalledPackagesBtn2 = document.getElementById('getLiteInstalledPackagesBtn2');
const packagesToUpgradeBtn2 = document.getElementById('getPackagesToUpgradeBtn2');
const installedPackageByNameBtn2 = document.getElementById('getInstalledPackageByNameBtn2');

// Agregar event listener a cada botón para paquetes extranjeros
installedPackagesBtn2.addEventListener('click', () => getInstalledPackages('foreign'));
liteInstalledPackagesBtn2.addEventListener('click', () => getLiteInstalledPackages('foreign'));
packagesToUpgradeBtn2.addEventListener('click', () => getPackagesToUpgrade('foreign'));
installedPackageByNameBtn2.addEventListener('click', () => getInstalledPackageByName('foreign'));
