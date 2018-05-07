<template id="new-edit-modal-template">
    <div class="owner">
        <form name="ownerForm" v-on:submit.prevent="saveOwner" v-on:reset="closeOwner">
            <div class="modal-body">
                <label class="form-label">
                    Фамилия
                    <input v-model.trim="owner.surname" class="form-control" required>
                </label>
                <label class="form-label">
                    Имя
                    <input v-model.trim="owner.name" class="form-control" required>
                </label>
                <label class="form-label">
                    Отчество
                    <input v-model.trim="owner.patronymic" class="form-control" required>
                </label>
                <label class="form-label">
                    Дата рождения
                    <datepicker v-model="owner.dateOfBirth" :inline="true"></datepicker>
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
    import Datepicker from "vuejs-datepicker";
    import moment from "moment";

    export default {
        components: {
            Datepicker
        },
        props: {
            owner: Object
        },
        data: function () {
            return {};
        },
        methods: {
            closeOwner: function () {
                this.$emit("closeOwner", {});
            },
            saveOwner: function () {
                console.log("Updating owner");
                this.owner.dateOfBirth = moment(this.owner.dateOfBirth).format(
                    "YYYY-MM-DD"
                );
                const url = "http://localhost:8080/driverApp/rest/owners";
                this.$http.post(url, this.owner).then(
                    resp => {
                        this.closeOwner();
                        this.$root.$emit("loadOwners", {});
                    },
                    error => console.log(error)
                );
            }
        }
    };
</script>

<style lang="scss">
    .owner {
        .modal-body {
            .form-label {
                display: block;
                margin: 5px 0;
            }
        }
    }
</style>
