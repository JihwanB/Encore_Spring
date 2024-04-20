<template>
    <!-- v-for (vue의 for문) : 반드시 기본키를 필요로 함!!! -->
    <div class="card mt-2" v-for="(work, index) in works" :key="work.id">

        <div class="card-body p-2 d-flex align-items-center" style="cursor: pointer" @click="readWork(work.id)">
            <div class="form-check flex-grow-1">
                <input type="checkbox" class="form-check-input" :value="work.completed"
                    @change="toggleWork(index, $event)" @click.stop>
                <label class="form-check-label" :class="{ work: work.completed }">
                    {{ work.subject }}
                </label>
            </div>
            <!-- 삭제를 위한 버튼 추가 -->
            <!-- <div>
                <button class="btn btn-danger btn-sm" @click.stop="deleteWork(index)">Delete</button>
            </div> -->
            <div>
                <button class="btn btn-danger btn-sm" @click.stop="openModal(work.id)">Delete</button>
            </div>
        </div>

    </div>

    <!-- Modal Component 추가 -->
    <Modal v-if="showModal" @modal-close="closeModal" @modal-delete="deleteWork" />

</template>

<script>
/*
props 사용시 주의점
- One Way Data Flow(부모 -> 자식 데이터 전달 X)
- 그래서 checkbox에서 completed를 자식에서 변경해서는 안됨
- 코드 수정이 필요한데, 데이터를 수정하려면 다시 부모에게 데이터를 전달해야 함
*/
import { useRouter } from 'vue-router';
import Modal from '@/components/ModalComponent.vue';
import { ref } from 'vue';

export default {
    components: {
        Modal
    },

    props: {
        works: {
            type: Array,
            required: true
        }
    },

    emits: [
        'toggle-work',
        'delete-work'
    ],

    setup(props, { emit }) {

        // 모달 처리를 위한 변수 선언. 삭제 카드목록 객체의 id
        const showModal = ref(false);
        const workDeleteId = ref(null);

        const openModal = (id) => {
            console.log(">>> debug openModal id , ", id);
            showModal.value = true;
            workDeleteId.value = id;
        }

        const closeModal = () => {
            showModal.value = false;
        }

        const toggleWork = (index, event) => {
            console.log("toggleWork target , ", event.target);
            console.log(`toggleWork target , ${event.target.checked}`);

            console.log(`toggleWork index , ${index}`);
            emit('toggle-work', index, event.target.checked);
        }

        // 모달 구현으로 인해서, 삭제 이벤트를 처리하는 함수 수정이 필요
        const deleteWork = () => {
            emit('delete-work', workDeleteId.value);
            showModal.value = false;
            workDeleteId.value = null;
        }

        const router = useRouter();
        const readWork = (id) => {
            // path를 이용한 페이지 이동
            // router.push(`/works/${id}`);

            // name을 이용한 페이지 이동 (위의 방법은 path가 바뀌면 모든 코드를 수정해야 하기 때문에, name을 이용한 방식이 좀 더 권장됨)
            router.push({
                name: "Work",
                params: {
                    id: id
                }
            });
        }

        return {
            toggleWork,
            deleteWork,
            readWork,
            showModal,
            openModal,
            closeModal,
        }
    }
}
</script>

<style></style>