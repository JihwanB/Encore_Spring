<template>

  <div class="container">

    <!-- create component add -->
    <div class="d-flex justify-content-between mb-3">
      <h2>Encore-Work List</h2>
      <button class="btn btn-primary" @click="moveToCreatePage">Create Work</button>
    </div>
    <!-- 검색기능 추가  -->
    <input type="text" placeholder="Search..." class="form-control" v-model="searchText">
    <hr/>
    <!-- form -> WorkForm.vue 이동 -->
    <!-- <WorkForm @add-work="addWork" /> -->
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
    <!-- vue v-for(반드시 기본키를 필요로 함!!) -->
    <WorkList :works="filterWorks" @toggle-work="toggleWork" @delete-work="deleteWork"/>

  </div>

  <Toast v-if="showToast" :message="toastMessage" :type="toastAlertType"/>

</template>

<script>
import {ref, computed} from 'vue';
import axios from '@/axios';
// list
import WorkList from "@/components/WorkList.vue";
// form
// import WorkForm from "@/components/WorkForm.vue";
// toast
import Toast from "@/components/ToastComponent.vue";
import {useToast} from "@/composable/toast";
// router
import {useRouter} from 'vue-router';
// import router from '../../router';

export default {
  components: {
    // WorkForm,
    WorkList,
    Toast
  },

  setup() {
    const searchText = ref('');
    const works = ref([]);
    const error = ref('');
    // toast.js에 있는 변수와 함수를 구조분해를 통해서 사용
    const {
      showToast,
      toastMessage,
      toastAlertType,
      triggerToast
    } = useToast();

    // index - 배열인덱스, 인덱스에 해당하는 객체의 id로 삭제
    // axios.delete()
    const deleteWork = async (id) => {
      error.value = '';
      // const work_id = works.value[index].id
      try {
        // json-server code
        // await axios.delete(`works/${id}`);
        // springboot code
        await axios.delete(`delete/${id}`);
        // await를 걸어서 이 작업이 끝날때까지 기다림
        // works.value.splice(index, 1);
        getWorks();
      } catch (err) {
        console.log(">> delete , ", err);
        error.value = "Something went wrong!";
      }

    }
    // const addWork = (work) => {
    //     console.log(">>>>>>>>>> emit work , ", work);
    //     works.value.push(work);
    // }

    // json-server code
    // const addWork = async (work) => {
    //     error.value = '';
    //     try {
    //         const res = await axios.post(
    //             'works', {
    //             id: Date.now(),
    //             subject: work.subject,
    //             completed: false
    //         });
    //         works.value.push(res.data);
    //     } catch (err) {
    //         console.log(err);
    //         error.value = "Something went wrong!";
    //     }
    // }
    const addWork = async (work) => {
      error.value = '';
      try {
        const res = await axios.post('works', {
          id: Date.now(),
          subject: work.subject,
          completed: false
        });
        works.value.push(res.data);
      } catch (err) {
        error.value = "Something went wrong!";
      }
    }

    const toggleWork = async (index, checked) => {
      // console.log("work completed", works.value[index].completed);
      // works.value[index].completed = !works.value[index].completed;
      // console.log("work completed", works.value[index].completed);
      console.log(">>>>>> WorkIndex.vue toggleWork checked, ", checked);
      error.value = '';
      try {
        const work_id = works.value[index].id
        await axios.patch(`works/${work_id}`, {
          completed: !works.value[index].completed
        });
        works.value[index].completed = checked;
      } catch (err) {
        console.log(">> put , ", err);
        error.value = "Something went wrong!";
      }
    }

    // javascript - filter(), includes()
    const filterWorks = computed(() => {
      if (searchText.value) {
        return works.value.filter((work) => {
          return work.subject.includes(searchText.value);
        });
      }
      return works.value;
    });

    // 전체데이터 가져오기 위한 통신 ....
    const getWorks = async () => {
      try {
        const res = await axios.get('works');
        works.value = res.data;
        console.log(">>>>> getWorks ok");
        console.log(">>>>> getWorks ok works , ", works.value[0]);
        triggerToast('Successfully loaded list!', 'info')
      } catch (err) {
        console.log(">> getWorks err", err);
        error.value = "something went wrong!"
        triggerToast('Something went wrong!', 'danger')
      }
    }

    // create work 이동을 위한 라우터 선언
    const router = useRouter();
    const moveToCreatePage = () => {
      router.push({
        name: 'WorkCreate'
      });
    }
    ///////
    getWorks();
    ///////
    return {
      works,
      deleteWork,
      addWork,
      toggleWork,
      searchText,
      filterWorks,
      error,
      showToast,
      toastMessage,
      toastAlertType,
      getWorks,
      moveToCreatePage,
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