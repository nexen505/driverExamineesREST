<template id="new-edit-modal-template">
    <div class="vehicle">
        <div class="modal-header">
            <h3>Данные машины</h3>
        </div>
        <form name="vehicleForm" v-on:submit.prevent="saveVehicle" v-on:reset="closeVehicle">
            <div class="modal-body">
                <label class="form-label">
                    Название
                    <input v-model="vehicle.name" class="form-control" required>
                </label>
                <label class="form-label">
                    Марка
                    <input v-model="vehicle.brand" class="form-control" required>
                </label>
                <label class="form-label">
                    Год выпуска
                    <input v-model="vehicle.yearOfIssue" class="form-control" required>
                </label>
                <label class="form-label">
                    Тип ТС
                    <select v-model="vehicle.type.id" required>
                        <option v-for="type in vehicleTypes" v-bind:key="type.id" v-bind:value="type.id">
                            {{ type.name }}
                        </option>
                    </select>
                </label>
            </div>

            <div class="modal-footer text-right">
                <button type="reset" class="btn btn-default">Отмена</button>
                <button type="submit" class="btn btn-primary modal-default-button">
                    <span class="glyphicon glyphicon-save" style="margin-top: 3px;"></span>Сохранить
                </button>
            </div>
        </form>

    </div>

</template>

<script>
    export default {
        props: {
            vehicle: Object
        },
        data: function () {
            return {
                vehicleTypes: []
            };
        },
        created() {
            let url = "http://localhost:8080/driverApp/rest/typesOfVehicle";
            this.$http.get(url).then(
                resp => {
                    this.vehicleTypes = resp.body.result;
                },
                error => console.log(error)
            );
        },
        methods: {
            closeVehicle: function () {
                this.$emit("closeVehicle", {});
            },

            saveVehicle: function () {
                console.log("Updating vehicle");
                const url = "http://localhost:8080/driverApp/rest/vehicles";
                this.$http.post(url, this.vehicle).then(
                    resp => {
                        this.closeVehicle();
                        this.$root.$emit("loadVehicles", {});
                    },
                    error => console.log(error)
                );
            }
        }
    };
</script>

<style lang="scss">
    .vehicle {
        .modal-body {
            .form-label {
                display: block;
                margin: 5px 0;
            }
        }
    }
</style>
