<template>
  <!--
  - https://github.com/jslim9413/encore-vue-pjt.git

  요구사항
  ############################################
  - Vuex : state management tool
  - npm install vuex@next --save
  - 상태관리를 위한 파일 src - store - index.js
  - main.js (vuex 등록하는 과정이 필요)
  - vuex 상태값을 변경할 때는 - mutation 사용해서 변경해야함.
  - mutation(동기) , action(비동기)
  - mutation : vue store 안에 있는 상태를 변경하는 유일한 방법
  - action   : vuex 사용하는 함수
  ############################################
  -->
  <nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container">
      <router-link class="navbar-brand" :to="{ name : 'Home' }">Hanwha SW Camp 5th</router-link>

      <ul class="navbar-nav me-auto">
        <li class="nav-item">
          <router-link class="nav-link active" aria-current="page" :to="{ name : 'Works'}">To-Work</router-link>
        </li>
      </ul>
    </div>
  </nav>

  <div class="container">
    <router-view/>
  </div>

</template>

<script>
import {useStore} from 'vuex' ;

export default {
  setup() {
    const store = useStore();
    console.log(">>> debug root App , ", store.state);
    console.log(">>> debug root App state showToast , ", store.state.showToast);
  }
}
</script>

<style>
</style>