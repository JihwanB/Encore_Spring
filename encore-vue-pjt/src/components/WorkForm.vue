<template>
    <form @submit.prevent="onSubmit">
        <div class="d-flex">
            <div class="flex-grow-1 mr-2">
                <input type="text" v-model="work" class="form-control" placeholder="new work...">
            </div>

            <div>
                <button class="btn btn-primary" type="submit">ADD</button>
            </div>
        </div>

        <!-- v-show 사용해서, null 값이 입력되었을 때, 오류메시지 출력 -->
        <div class="error" v-show="hasError">
            This field cannot be empty.
        </div>

    </form>
</template>

<script>
import { ref } from 'vue';

export default {

    emits: [
        'add-work'
    ],

    setup(props, { emit }) {
        const work = ref('');
        const hasError = ref(false);


        const onSubmit = () => {
            if (work.value === '') {
                hasError.value = true;
                return;
            }
            emit("add-work", {
                idx: Date.now(),
                subject: work.value,
                completed: false
            });
            hasError.value = false;
            work.value = '';
        }

        return {
            work,
            hasError,
            onSubmit,
        }
    }
}
</script>

<style></style>