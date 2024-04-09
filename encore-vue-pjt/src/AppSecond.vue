<template>
    <div class="container">
        <h2>양방향 바인딩(script -> template, template -> script)</h2>
        <div :class="className">{{ name }}</div> <!-- "v-bind"는 생략 가능 (클래스나 속성에 대한 바인딩) -->
        <input :type="type" v-model="name" value="msg"> <!-- "v-model"은 양방향 바인딩 -->
        <button class="btn btn-info" @click="onSubmit">Click</button> <!-- "v-on"은 @로 대체 가능 -->
    </div>
</template>

<script>
import { ref } from 'vue';

export default {

    setup() {
        let name = ref("encore");
        let className = ref('');
        let type = ref('text');

        let changeClass = () => {
            className.value = 'name';
            type.value = 'text';
        }
        let onSubmit = () => {
            console.log(name.value);
        }

        return {
            name,
            className,
            changeClass,
            type,
            onSubmit
        }
    }

}
</script>

<style>
.name {
    color: red;
}
</style>