<template>
    <!-- v-show, v-if, v-else -->
    <!-- 
    <div v-show="toggle">true</div>
    <div v-show="!toggle">false</div>
    <div v-if="toggle">true</div>
    <div v-if="!toggle">false</div>
    <button @click="onToggle">Toggle</button> 
    -->

    <!--
    요구사항
    - 최초 페이지 로딩 시, 입력된 work가 없습니다 메시지 출력
    - 배열의 길이(length)를 체크 -> v-if 사용
    ================================================
    - 카드 목록에 버튼(delete) 추가하되, 이벤트 버블링 고려 필요
    -->

    <div class="container">
        <h2>Encore-Work List</h2>
        <!-- form -> WorkForm.vue로 이동 -->
        <WorkForm />


        <!-- 페이지 로딩시 추가되는 코드 -->
        <div v-if="!works.length" class="error">
            등록한 일정이 존재하지 않습니다.
        </div>

        <!-- v-for (vue의 for문) : 반드시 기본키를 필요로 함!!! -->
        <div class="card mt-2" v-for="(work, index) in works" :key="work.id">
            <div class="card-body p-2 d-flex align-items-center">
                <div class="form-check flex-grow-1">
                    <input type="checkbox" class="form-check-input" v-model="work.completed" @click="check">
                    <label class="form-check-label" :class="{ work: work.completed }">
                        {{ work.subject }}
                    </label>
                </div>
                <!-- 삭제를 위한 버튼 추가 -->
                <div>
                    <button class="btn btn-danger btn-sm" @click="deleteWork(index)">Delete</button>
                </div>
            </div>
        </div>
    </div>

</template>

<!--
npm i axios
import axios from 'axios';
axios.get(); == $.ajax();
-->

<script>
import { ref } from 'vue';
import WorkForm from "./components/WorkForm.vue";

export default {
    components: {
        WorkForm
    },

    setup() {
        const work = ref('');
        const works = ref([]);
        // let cnt = 0;
        const toggle = ref(false);
        const hasError = ref(false);


        const onSubmit = () => {
            if (work.value === '') {
                hasError.value = true;
                return;
            }
            works.value.push({
                idx: Date.now(),
                subject: work.value,
                completed: false
            });
            hasError.value = false;
            work.value = '';
        }

        const deleteWork = (index) => {
            console.log(`delete click ${index}`);
            works.value.splice(index, 1);
        }

        // const onToggle = () => {
        //     toggle.value = !toggle.value;
        // }

        return {
            onSubmit,
            work,
            works,
            // cnt,
            toggle,
            // onToggle,
            hasError,
            deleteWork
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