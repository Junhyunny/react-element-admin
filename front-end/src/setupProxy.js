const {createProxyMiddleware} = require('http-proxy-middleware');

module.exports = function (app) {
    app.use(
        '/backend_service',
        createProxyMiddleware({
            target: 'http://192.168.0.2:8080',
            changeOrigin: true,
            pathRewrite: {
                '^/backend_service': ''
            }
        })
    );
};