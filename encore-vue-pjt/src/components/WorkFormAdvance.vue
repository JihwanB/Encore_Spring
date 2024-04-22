<template>

  <div v-if="loading">
    Loading...
  </div>

  <form v-else @submit.prevent="onSave">

    <div class="row">
      <div class="col-6">
        <div class="form-group">
          <label>Subject</label>
          <input type="text" class="form-control" v-model="work.subject">
          <!-- 유효성 체크 (subject에 null 입력시 동작하는 영역) -->
          <div v-if="subjectError" style="color : red">
            {{ subjectError }}
          </div>
        </div>
      </div>

      <!-- 수정 페이지에서 이동했을 떄 보여져야 함 -->
      <div class="col-6" v-if="editing">
        <div class="form-group">
          <label>Status</label>
          <div>
            <button class="btn" type="button" :class="work.completed ? 'btn-success' : 'btn-danger'"
                    @click="toggleWorkStatus">
              {{ work.completed ? 'Completed' : 'Incomplete' }}
            </button>
          </div>
        </div>
      </div>

      <div class="col-12">
        <div class="form-group">
          <label>Content</label>
          <textarea v-model="work.content" class="form-control" cols="30" rows="10"></textarea>
        </div>
      </div>
    </div>

    <button type="submit" class="btn btn-primary" disable="!workUpdated">
      {{ editing ? 'Update' : 'Create' }}
    </button>
    <button class="btn btn-outline-dark ml-2" @click="moveToWorkList">
      Cancel
    </button>

  </form>

  <Toast v-if="showToast" :message="toastMessage" :type="toastAlertType"/>

</template>

<script>
import {useRoute, useRouter} from 'vue-router';
import {ref, computed} from 'vue';
import axios from '@/axios';
import _ from 'lodash';
import Toast from '@/components/ToastComponent.vue';
import {useToast} from '@/composable/toast';

// vuex add
import { useStore } from 'vuex' ;

export default {
  components: {
    Toast
  },

  props: {
    editing: {
      type: Boolean,
      default: false
    }
  },

  setup(props) {
    //
    const store = useStore();
    console.log(">>> debug workform component , " , store.state);
    console.log(">>> debug workform component state showToast , " , store.state.showToast);

    ////////////////////////////////////// 변수
    const loading = ref(false);
    const router = useRouter();
    const route = useRoute();
    // 기본키 값을 이용해서 수정페이지 화면에 데이터 출력
    const work_id = route.params.id;

    const work = ref({
      subject: '',
      complete: false,
      content: ''
    });
    const copyWork = ref(null);
    const {
      showToast,
      toastMessage,
      toastAlertType,
      triggerToast
    } = useToast();

    // subject에 널값 입력시 유효성 체크
    const subjectError = ref('');
    ///////////////////////////////////// 함수
    const workUpdated = computed(() => {
      return _.isEqual(work.value, copyWork.value);
    });

    // 서버통신을 통해서 키에 해당하는 객체 정보를
    const getWork = async () => {
      console.log(">>> debug getWork params.id , ", work_id);
      try {
        const res = await axios.get(`find/${work_id}`);
        work.value = {...res.data};
        copyWork.value = {...res.data};
        loading.value = false;
      } catch (err) {
        console.log("View getWork err , ", err);
        loading.value = true;
        triggerToast("Something went wrong", 'danger');
      }
    }
    // 등록일 때는 호출되어서는 안됨!!!
    if (props.editing) {
      getWork();
    }

    // 서버통신을 통해서 데이터 등록 / 수정
    const onSave = async () => {
      subjectError.value = '';
      if (work.value.subject === '') {
        subjectError.value = 'Subject is required';
        return;
      }
      try {
        let res;
        const data = {
          // id: Date.now(),
          // springboot 통신에서는 사용하지 않음.
          // id : Date.now(),
          subject: work.value.subject,
          content: work.value.content,
          completed: work.value.completed,
        };
        if (props.editing) {
          // 수정할 때 사용할 부분 (update)
          const res = await axios.put(`update`, {
            id        : work_id,
            subject   : work.value.subject ,
            completed : work.value.completed,
            content   : work.value.content
          });
          console.log(">>>>> onUpdate put res , ", res.data);
          work.value = {...res.data};

          router.push({
            name: 'Works'
          });

          // triggerToast("Successfully updated!", 'info');

        } else {
          // 새로운 추가할 때 사용할 부분 (create)
          // res = await axios.post('works', data); ... json-server

          // springboot
          res = await axios.post('save', data);

          work.value.subject = '';
          work.value.content = '';
          console.log(">>> , ", res.data);

          router.push({
            name: 'Works'
          });
        }
        // triggerToast("Successfully created!", 'info');
      } catch (err) {
        console.log("update err , ", err);
        triggerToast("Something went wrong", 'danger');
      }
    }

    const moveToWorkList = () => {
      router.push({
        name: 'Works'
      });
    }
    //status change
    const toggleWorkStatus = () => {
      console.log("work completed , ", work.value.completedb
      work.value.completed = !work.value.completed;
      console.log("work completed , ", work.value.completed);
    }

    return {
      loading,
      work,
      workUpdated,
      showToast,
      toastMessage,
      toastAlertType,
      subjectError,
      triggerToast,
      onSave,
      moveToWorkList,
      toggleWorkStatus,
      getWork,
    }

  }

}
</script>

<style></style>
