<template>
    <!--
    - 카드목록에서 선택되었을 때, 해당페이지가 로딩되고
    - getWork() : axios 통신을 이용해서 데이터를 가져오고
    - input text 영역에 데이터를 출력

    - 객체 복사
    - 복사된 객체의 속성을 비교하기 위해서 lodash
    - npm install lodash
    -->
    <h2>Work Read</h2>

    <div class="spinner-border text-primary" role="status" v-if="loading">
        <span class="visually-hidden">Loading...</span>
    </div>

    <form v-else @submit.prevent="onUpdate">

        <div class="row">
            <div class="col-6">
                <div class="form-group">
                    <label>Work Subject</label>
                    <input type="text" class="form-control" v-model="work.subject">
                </div>
            </div>
            <div class="col-6">
                <div class="form-group">
                    <label>Work Status</label>
                    <div>
                        <!-- <button class="btn btn-danger">
                            Incomplete
                        </button> -->
                        <button type="button" :class="work.completed ? 'btn btn-success' : 'btn btn-danger'"
                            @click="toggleWorkStatus">
                            {{ work.completed ? 'Completed' : 'Incomplete' }}
                        </button>

                    </div>
                </div>
            </div>
        </div>

        <button class="btn btn-primary" :disabled="!workUpdated">Save</button>
        <button class="btn btn-outline-dark ml-2" @click.prevent="moveToWorkList">Cancel</button>

    </form>

</template>

<script>
import { useRoute, useRouter } from 'vue-router'
import { ref } from 'vue';
import axios from '@/axios';
import _ from 'lodash';
import { computed } from 'vue';

export default {

    setup() {
        const route = useRoute();
        const router = useRouter();
        console.log(route.params.id);
        ///////////////
        const loading = ref(true);

        // 원본
        const work = ref(null);

        // 복사본
        const copyWork = ref(null);

        const getWork = async () => {
            try {
                const res = await axios.get(`works/${route.params.id}`)
                console.log("View getWork res.data , ", res.data);
                work.value = { ...res.data };
                copyWork.value = { ...res.data };
                loading.value = false;
            }
            catch (err) {
                console.error("View getWork err, ", err);
                loading.value = true;
            }
        };
        getWork();

        const moveToWorkList = () => {
            router.push({
                name: "Works",
            });
        };

        const toggleWorkStatus = async () => {
            console.log("work completed , ", work.value.completed);
            work.value.completed = !work.value.completed;
            console.log("work completed , ", work.value.completed);
        }

        const onUpdate = async () => {
            try {
                const res = await axios.put(`works/${route.params.id}`, {
                    subject: work.value.subject,
                    completed: work.value.completed
                });
                console.log("View onUpdate res.data , ", res.data);
                router.push({
                    name: "Works",
                });
            }
            catch (err) {
                console.error("View onUpdate err, ", err);
            }
        }

        const workUpdated = computed(() => {
            // 객체 내부의 모든 속성의 값이 일치하는지 확인
            return !_.isEqual(work.value, copyWork.value);
        });


        return {
            loading,
            work,
            getWork,
            moveToWorkList,
            toggleWorkStatus,
            onUpdate,
            copyWork,
            workUpdated,
            computed,
        }
    }
}
</script>

<style></style>