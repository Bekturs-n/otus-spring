const {defineConfig} = require('@vue/cli-service')

module.exports = defineConfig({
    transpileDependencies: true,
    devServer: {
        port: 9000,
        proxy: {
            '^/students/*': {
                target: "http://localhost:8080",
                secure: false,
                changeOrigin: true
            }
        }
    }
})
