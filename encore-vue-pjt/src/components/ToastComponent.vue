<template>
    <!-- toast 작성을 위해서 부트스트랩 참고 -->
    <div class="alert toast-box" :class="`alert-${type}`" role="alert">
        {{ message }}
    </div>
</template>

<script>
export default {

    props: {
        message: {
            type: String,
            required: true
        },
        type: {
            type: String,
            default: 'success'
        }
    }

}
</script>

<style>
.toast-box {
    position: fixed;
    top: 10px;
    right: 10px;
}
</style>