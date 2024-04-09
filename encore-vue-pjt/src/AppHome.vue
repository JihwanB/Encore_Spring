<template>
    <!-- v-show, v-if, v-else -->
    <!-- 
    <div v-show="toggle">true</div>
    <div v-show="!toggle">false</div>
    <div v-if="toggle">true</div>
    <div v-if="!toggle">false</div>
    <button @click="onToggle">Toggle</button> 
    -->

    <div class="container">
        <h2>Encore-Work List</h2>
        <form @submit.prevent="onSubmit">
            <div class="d-flex">
                <div class="flex-grow-1 mr-2">
                    <input type="text" class="form-control" placeholder="new work..." v-model="work">
                </div>

                <div>
                    <button class="btn btn-primary">ADD</button>
                </div>
            </div>
            <!-- v-show 사용해서, null 값이면 안보이게 처리 -->
            <div v-show="hasError" class="error">
                This field cannot be empty.
            </div>

        </form>

        <!-- v-for (vue의 for문) : 반드시 기본키를 필요로 함!!! -->
        <div class="card mt-2" v-for="work in works" :key="work.id">
            <div class="card-body p-2">
                <div class="form-check">
                    <input type="checkbox" class="form-check-input" v-model="work.completed" @click="check">
                    <label class="form-check-label" :class="{ work: work.completed }">
                        {{ work.subject }}
                    </label>
                </div>
            </div>
        </div>
    </div>

</template>

<script>
import { ref } from 'vue';

export default {
    setup() {
        const work = ref('');
        const works = ref([]);
        let cnt = 0;
        const toggle = ref(false);
        const hasError = ref(false);

        const onSubmit = () => {
            if (work.value === '') {
                hasError.value = true;
                return;
            }
            works.value.push({
                idx: cnt++,
                subject: work.value,
                completed: false
            });
            hasError.value = false;
            work.value = '';
        }

        const check = () => {

        }

        const onToggle = () => {
            toggle.value = !toggle.value;
        }

        return {
            onSubmit,
            work,
            works,
            cnt,
            toggle,
            onToggle,
            hasError,
            check
        }
    }
}

</script>

<style>
.error {
    color: red;
}

.work {
    color: green;
    text-decoration: line-through;
}
</style>