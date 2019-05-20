module.exports = {
    baseUrl: './',
    productionSourceMap: false,
    devServer: {
        port: '8080',
        proxy: {
            '/api':{
                // target:'http://119.23.105.191:8088',
                target:'http://192.168.1.101:8088',
                changeOrigin:true,
                pathRewrite:{
                    '/api':''
                }
            },
            '/ms':{
                target: 'https://www.easy-mock.com/mock/592501a391470c0ac1fab128',
                changeOrigin: true
            }
        }
    }
}