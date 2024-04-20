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

    <WorkForm :editing="true" />

    <!-- 기존 work read 코드
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

                        <button type="button" :class="work.completed ? 'btn btn-success' : 'btn btn-danger'"
                            @click="toggleWorkStatus">
                            {{ work.completed ? 'Completed' : 'Incomplete' }}
                        </button>

                    </div>
                </div>
            </div>
        </div>

        <button class="btn btn-primary" :disabled="!workUpdated">Save</button>
        <button class="btn btn-outline-dark ml-2" @click="moveToWorkList">Cancel</button>
    </form>

    <Toast v-if="showToast" :message="toastMessage" :type="toastAlertType" />
     -->

    <!-- 컴포넌트 생명주기 테스트 -->
    <!-- <div id="encore">encore</div> -->

</template>

<script>
// import { useRoute, useRouter } from 'vue-router';
// import { computed, ref } from 'vue';
// import { computed, ref, onBeforeMount, onMounted, onBeforeUpdate, onUpdated, onBeforeUnmount, onUnmounted } from 'vue';
// import axios from '@/axios';
// import _ from 'lodash';

//toast
// import Toast from "@/components/ToastComponent.vue";
// import { useToast } from "@/composable/toast";

/*
컴포넌트의 생명주기
onMounted() : DOM에 컴포넌트가 생성되고 나서 실행되는 함수
onUpdated() : 컴포넌트(혹은 컴포넌트가 갖고있는 데이터)가 업데이트 되고 나서 실행되는 함수
onUnmounted() : DOM에서 컴포넌트가 제거되고 나서 실행되는 함수
*/
import WorkForm from "@/components/WorkFormAdvance.vue";

export default {
    components: {
        WorkForm,
        // Toast,
    },

    setup() {
        //     const route = useRoute();
        //     const router = useRouter();

        //     console.log(route.params.id);
        //     /////////////
        //     const loading = ref(true);

        //     // 원본
        //     const work = ref(null);
        //     // 복사복
        //     const copyWork = ref(null);

        //     //toast 위한 변수 추가
        //     const {
        //         showToast,
        //         toastMessage,
        //         toastAlertType,
        //         triggerToast
        //     } = useToast();

        //     const getWork = async () => {
        //         try {
        //             const res = await axios.get(`works/${route.params.id}`);
        //             work.value = { ...res.data };
        //             copyWork.value = { ...res.data };
        //             loading.value = false;
        //         } catch (err) {
        //             console.log("View getWork err , ", err);
        //             loading.value = true;
        //             triggerToast("Something went wrong", 'danger');
        //         }
        //     }
        //     getWork();

        //     const moveToWorkList = () => {
        //         router.push({
        //             name: 'Works'
        //         });
        //     }

        //     const toggleWorkStatus = () => {
        //         console.log("work completed , ", work.value.completed);
        //         work.value.completed = !work.value.completed;
        //         console.log("work completed , ", work.value.completed);
        //     }

        //     // 서버통신 통해서 데이터 수정 
        //     const onUpdate = async () => {
        //         // window.alert(">>>>> click");
        //         try {
        //             const res = await axios.put(`works/${route.params.id}`, {
        //                 subject: work.value.subject,
        //                 completed: work.value.completed
        //             });
        //             console.log(">>>>> onUpdate put res , ", res.data);

        //             router.push({
        //                 name: 'Works'
        //             });

        //             triggerToast("Successfully updated!", 'info');
        //         } catch (err) {
        //             console.log("update err , ", err);
        //             triggerToast("Something went wrong", 'danger');
        //         }
        //     }

        //     const workUpdated = computed(() => {
        //         // 객체내부의 모든 속성의 값이 일치하는지 확인 
        //         return !_.isEqual(work.value, copyWork.value);
        //     });

        //     return {
        //         loading,
        //         work,
        //         moveToWorkList,
        //         toggleWorkStatus,
        //         onUpdate,
        //         workUpdated,
        //         showToast,
        //         toastMessage,
        //         toastAlertType,
        //     }
    }
}
</script>

<style></style>