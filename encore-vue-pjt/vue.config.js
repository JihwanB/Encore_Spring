const {defineConfig} = require('@vue/cli-service')
module.exports = defineConfig({
    transpileDependencies: true,
    // devServer: {
    //   proxy: {
    //     '/api': {
    //       target: 'http://localhost:8972', // 프록시할 대상 서버의 주소
    //       changeOrigin: true, // 프록시 대상의 호스트를 원래 요청의 호스트로 변경
    //       rewrite: (path) => path.replace(/^\/api/, ""),
    //     }
    //   }
    // }
})
