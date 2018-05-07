<template id="grid-template">
    <table>
        <thead>
        <tr>
            <th v-for="(value, key) in columns" v-on:click="sortBy(key)" :key="key"
                :class="{ active: sortKey === key }">
                {{ value | capitalize }}
                <span class="arrow" :class="sortOrders[key] > 0 ? 'asc' : 'dsc'"></span>
            </th>
            <th>Действия</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="entry in filteredData" :key="entry.id">
            <td v-for="key in Object.keys(columns)" :key="key">
                {{entry[key]}}
            </td>
            <td>
                <button class="btn btn-default" v-on:click="editRow(entry)">
                    <span class="glyphicon glyphicon-edit" style="margin-top: 3px;"></span> Редактировать
                </button>
                <button class="btn btn-default" v-on:click="deleteRow(entry)">
                    <span class="glyphicon glyphicon-delete" style="margin-top: 3px;"></span> Удалить
                </button>
            </td>
        </tr>
        </tbody>
    </table>
</template>

<script>
    export default {
        props: {
            data: Array,
            columns: Object,
            filterKey: String,
            deleteUrl: String
        },
        data: function () {
            let sortOrders = {};
            Object.entries(this.columns).forEach(([key, value]) => {
                sortOrders[key] = 1;
            });
            return {
                gridData: [],
                sortKey: "",
                sortOrders: sortOrders
            };
        },
        computed: {
            filteredData: function () {
                const sortKey = this.sortKey,
                    filterKey = this.filterKey && this.filterKey.toLowerCase(),
                    order = this.sortOrders[sortKey] || 1;
                let data = this.data;
                if (filterKey) {
                    data = data.filter(row =>
                        Object.keys(row).some(
                            key =>
                                String(row[key])
                                    .toLowerCase()
                                    .indexOf(filterKey) > -1
                        )
                    );
                }
                if (sortKey) {
                    data = data.slice().sort((a, b) => {
                        a = a[sortKey];
                        b = b[sortKey];
                        return (a === b ? 0 : a > b ? 1 : -1) * order;
                    });
                }
                return data;
            }
        },
        filters: {
            capitalize: function (str) {
                return str.charAt(0).toUpperCase() + str.slice(1);
            }
        },
        methods: {
            sortBy: function (key) {
                this.sortKey = key;
                this.sortOrders[key] = this.sortOrders[key] * -1;
            },
            deleteRow: function (row) {
                alert(`This row will be deleted: ${row.id}`);
                this.$http
                    .delete(`${this.deleteUrl}/${row.id}`)
                    .then(
                        res => {
                            this.data.splice(this.data.indexOf(row), 1);
                        },
                        error => console.log(error)
                    );
            },
            editRow: function (row) {
                this.$emit("edit", row);
            }
        }
    };
</script>

<style lang="scss">
    body {
        font-family: Helvetica Neue, Arial, sans-serif;
        font-size: 14px;
        color: #444;
    }

    table {
        border: 2px solid #42b983;
        border-radius: 3px;
        background-color: #fff;
    }

    th {
        background-color: #42b983;
        color: rgba(255, 255, 255, 0.66);
        cursor: pointer;
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
    }

    td {
        background-color: #f9f9f9;
    }

    th,
    td {
        min-width: 120px;
        padding: 10px 20px;
    }

    th.active {
        color: #fff;
        .arrow {
            opacity: 1;
        }
    }

    .arrow {
        display: inline-block;
        vertical-align: middle;
        width: 0;
        height: 0;
        margin-left: 5px;
        opacity: 0.66;
        &.asc {
            border-left: 4px solid transparent;
            border-right: 4px solid transparent;
            border-bottom: 4px solid #fff;
        }
        &.dsc {
            border-left: 4px solid transparent;
            border-right: 4px solid transparent;
            border-top: 4px solid #fff;
        }
    }
</style>