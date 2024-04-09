<template>

  <div class="name">{{ name }}</div>
  <div>{{ greeting('Vue') }}</div>
  <div>{{ greet }}</div>
  <button class="btn btn-danger" v-on:click="updateName">Click</button>

</template>

<script>
/*
Vue.js -> SPA (Single Page Application)
  - 반응형 데이터 (스크립트에서 데이터 변경 -> template에 반영)
  - reactive state (서로간의 상태가 동기화 되어있는 상태)
  - ref(), reactive() : 이 함수들을 사용하여 변수를 선언해야지만 reactive state 유지
    - ref() : primitive type (number, string, boolean)
    - reactive() : object type (array, object)
composition API 사용하는 영역
  - setup() 함수정의 (Vue3)
  */
import { ref } from 'vue';

export default {

  setup() {
    // let name = "Hanwha SW Camp 5th";
    let name = ref("Hanwha SW Camp 5th");

    // const greeting = () => {
    //   return "Hello";
    // }
    const greeting = (name) => {
      return `Hello, ${name}`;
    }

    let greet = greeting(name.value);

    const updateName = () => {
      // window.alert("데이터 변경");
      name.value = "데이터 변경";
      console.log(name)
    }

    return {
      name,
      greeting,
      greet,
      updateName
    }
  }
}
</script>

<style>
.name {
  color: red;
}
</style>