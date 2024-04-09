<template>
    <form>
        <div class="d-flex">
            <div class="flex-grow-1 mr-2">
                <input type="text" class="form-control" placeholder="new work...">
            </div>

            <div>
                <button class="btn btn-primary">ADD</button>
            </div>
        </div>

        <!-- v-show 사용해서, null 값이 입력되었을 때, 오류메시지 출력 -->
        <div class="error">
            This field cannot be empty.
        </div>

    </form>
</template>

<script>
export default {

}
</script>

<style></style>