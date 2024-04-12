<template>

    <!--
    요구사항
    ################################################
    가상의 DB 서버로 json-server 사용
    - npm install -g json-server@0.17.0
    - root > encore.json 파일 생성
    - json-server --watch encore.json
    ############### ajax - axios
    - npm i axios
    ################################################

    부모 컴포넌트와 자식 컴포넌트 사이에서 데이터 전송
    - setup(props, context)
    - context.emit('이벤트명', 데이터) => 부모에 데이터 전달
    - props => 자식에게 데이터 전달
    ################################################
    -->

    <div class="container">
        <h2>Encore-Work List</h2>
        <!-- 검색기능 추가 -->
        <input type="text" placeholder="Search..." class="form-control" v-model="searchText">
        <hr />

        <!-- form -> WorkForm.vue로 이동 -->
        <WorkForm @add-work="addWork" />


        <!-- 페이지 로딩시 추가되는 코드 -->
        <!-- <div v-if="!works.length" class="error">
            등록한 일정이 존재하지 않습니다.
        </div> -->

        <div class="error">
            {{ error }}
        </div>

        <!-- 검색기능 추가하는 서치 바 -->
        <div v-if="!filterWorks.length" class="error">
            필터링된 결과가 존재하지 않습니다.
        </div>

        <WorkList :works="filterWorks" @toggle-work="toggleWork" @delete-work="deleteWork" />

    </div>

</template>

<!--
npm i axios
import axios from 'axios';
axios.get(); == $.ajax();
-->

<script>
import { ref, computed } from 'vue';
import axios from 'axios';

import WorkForm from "./components/WorkForm.vue";
import WorkList from "./components/WorkList.vue";

export default {
    components: {
        WorkForm,
        WorkList
    },

    setup() {
        const works = ref([]);
        const searchText = ref('');
        const error = ref('');

        // 통신 이전 version
        // const addWork = (work) => {
        //     console.log(">>>>> emit work, ", work);
        //     works.value.push(work);
        // }

        const addWork = async (work) => {
            error.value = '';
            try {
                const res = await axios.post('http://localhost:3000/works', {
                    id: Date.now(),
                    subject: work.subject,
                    completed: false
                });
                works.value.push(res.data);
            }
            catch (err) {
                console.log(">> addWork error , ", err);
                error.value = 'Something went wrong!';
            }
        }

        const deleteWork = async (index) => {
            error.value = '';
            const work_id = works.value[index].id;
            try {
                await axios.delete(`http://localhost:3000/works/${work_id}`);
                works.value.splice(index, 1);
            }
            catch (err) {
                console.log(">> deleteWork error , ", err);
                error.value = 'Something went wrong!';
            }
        }

        const toggleWork = async (index) => {
            error.value = '';
            try {
                const res = await axios.patch(`http://localhost:3000/works/${works.value[index].id}`, {
                    completed: !works.value[index].completed
                });
                works.value[index] = res.data;
            }
            catch (err) {
                console.log(">> toggleWork error , ", err);
                error.value = 'Something went wrong!';
            }
        }

        // javascript - filter(), includes()
        const filterWorks = computed(() => {
            if (searchText.value) {
                return works.value.filter(work => {
                    return work.subject.includes(searchText.value);
                })
            }
            return works.value
        });

        // 전체데이터 가져오기 위한 통신
        const getWorks = async () => {
            try {
                const res = await axios.get('http://localhost:3000/works');
                works.value = res.data;
            }
            catch (err) {
                console.log(">> getWorks error , ", err);
                error.value = 'Something went wrong!';
            }
        }
        ////////////
        getWorks();
        ////////////

        return {
            works,
            deleteWork,
            addWork,
            toggleWork,
            searchText,
            filterWorks,
            error,
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