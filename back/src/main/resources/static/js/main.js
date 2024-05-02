var stockApi = Vue.resource('/api/stocks{/id}');

Vue.component('stock-row', {
    props: ['stock'],
    template: '<div><div>[{{ stock.id }}]{{ stock.status }}</div> <div>{{ stock.open_time}} - {{ stock.close_time }}</div></div>'
});

Vue.component('stocks-list', {
    props: ['stocks'],
    template: '<div> <stock-row v-for="stock in stocks" :key="stock.id" :stock="stock" /> </div>',
    created: function() {
        stockApi.get().then(res => {
            res.json().then( data => {
                    data.forEach(stock => this.stocks.push(stock));
                }
            )
        })
    }
});

var app = new Vue({
    el: '#app',
    template: '<stocks-list :stocks="stocks"/>',
    data: {
        stocks: [

        ]
    }
});