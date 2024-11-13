addEventListener("load", () => {
    const btnSubmitJugador = document.querySelector('.submit.jugador');
    const btnSubmitTrofeo = document.querySelector('.submit.trofeo');
    const btnAssignTrofeo = document.querySelector('.submit.asignar');
    const tableJugadores = document.querySelector('.lista-jugadores');
    const tableTrofeos = document.querySelector('.lista-trofeos');
    const jugadorSelect = document.querySelector('#jugadorId');
    const trofeoSelect = document.querySelector('#trofeoId');

    const urlJugadores = "http://localhost:8080/api/jugadores";
    const urlTrofeos = "http://localhost:8080/api/trofeos";

    loadJugadores();
    loadTrofeos();
    loadDropdowns();

    // Crear Jugador
    btnSubmitJugador.addEventListener('click', e => {
        e.preventDefault();

        const formData = new FormData(document.forms.createJugador);
        const data = { nombreUsuario: formData.get("nombreJugador") };

        fetch(urlJugadores, {
            method: "POST",
            body: JSON.stringify(data),
            headers: { 'Content-Type': 'application/json' }
        })
        .then(() => {
            loadJugadores();
            loadDropdowns();
        })
        .catch(err => console.error(err));
    });

    // Crear Trofeo
    btnSubmitTrofeo.addEventListener('click', e => {
        e.preventDefault();

        const formData = new FormData(document.forms.createTrofeo);
        const data = {
            titulo: formData.get("tituloTrofeo"),
            descripcion: formData.get("descripcionTrofeo")
        };

        fetch(urlTrofeos, {
            method: "POST",
            body: JSON.stringify(data),
            headers: { 'Content-Type': 'application/json' }
        })
        .then(() => {
            loadTrofeos();
            loadDropdowns();
        })
        .catch(err => console.error(err));
    });

    // Asignar Trofeo a Jugador
    btnAssignTrofeo.addEventListener('click', e => {
        e.preventDefault();

        const formData = new FormData(document.forms.assignTrofeo);
        const jugadorId = formData.get("jugadorId");
        const trofeoId = formData.get("trofeoId");

        fetch(`${urlJugadores}/${jugadorId}/trofeos/${trofeoId}`, {
            method: "POST"
        })
        .then(() => {
            loadJugadores();
            loadTrofeos();
        })
        .catch(err => console.error(err));
    });

    function loadJugadores() {
        fetch(urlJugadores)
            .then(response => response.json())
            .then(jugadores => {
                updateTable(tableJugadores, jugadores, jugador => [
                    jugador.nombreUsuario,
                    jugador.trofeos.map(t => t.titulo).join(", ")
                ], ["Nombre", "Trofeos", ""]);
            })
            .catch(err => console.error(err));
    }

    function loadTrofeos() {
        fetch(urlTrofeos)
            .then(response => response.json())
            .then(trofeos => {
                updateTable(tableTrofeos, trofeos, trofeo => [
                    trofeo.titulo,
                    trofeo.descripcion,
                    trofeo.jugadores.map(t => t.nombreUsuario).join(", ")
                ], ["Nombre", "Descripción", "Jugadores con este trofeo"]);
            })
            .catch(err => console.error(err));
    }

    function loadDropdowns() {
        fetch(urlJugadores)
            .then(response => response.json())
            .then(jugadores => {
                updateDropdown(jugadorSelect, jugadores, jugador => ({
                    value: jugador.idJugador,
                    text: jugador.nombreUsuario
                }));
            });

        fetch(urlTrofeos)
            .then(response => response.json())
            .then(trofeos => {
                updateDropdown(trofeoSelect, trofeos, trofeo => ({
                    value: trofeo.idTrofeo,
                    text: trofeo.titulo
                }));
            });
    }

    function updateTable(table, data, rowMapper, headers) {
        table.innerHTML = "";
        const header = table.insertRow();
        headers.forEach(h => {
            const th = document.createElement("th");
            th.textContent = h;
            header.appendChild(th);
        });

        data.forEach(item => {
            const row = table.insertRow();
            rowMapper(item).forEach(cellData => {
                const cell = row.insertCell();
                cell.textContent = cellData;
            });

            const deleteCell = row.insertCell();
            const deleteButton = document.createElement("button");
            deleteButton.textContent = "Eliminar";
            deleteButton.addEventListener("click", () => {
                const url = table === tableJugadores ? `${urlJugadores}/${item.idJugador}` : `${urlTrofeos}/${item.idTrofeo}`;
                fetch(url, { method: "DELETE" })
                .then(() => {
                    loadJugadores();
                    loadTrofeos();
                })
                .catch(err => console.error(err));
            });
            deleteCell.appendChild(deleteButton);
        });
    }

    function updateDropdown(select, data, optionMapper) {
        select.innerHTML = '<option value="">--Seleccione una opción--</option>';
        data.forEach(item => {
            const option = document.createElement("option");
            const { value, text } = optionMapper(item);
            option.value = value;
            option.textContent = text;
            select.appendChild(option);
        });
    }
});
