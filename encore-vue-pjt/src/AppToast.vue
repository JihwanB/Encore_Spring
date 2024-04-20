<template>
    <!-- v-show, v-if, v-else -->
    <!-- <div v-show="toggle">true</div>
  <div v-show="!toggle">false</div> -->

    <!-- <div v-if="toggle">true</div>
  <div v-else>false</div>

  <button @click="onToggle">Toggle</button> -->


    <!--
     #########
     요구사항
    - 알림 컴포넌트 (Toast) 작성
    - 애니메이션 (fade-in , fade-out, slide)
    - 전역 형식으로
    - 컴포넌트의 라이프사이클에 대해서 살펴보자.
     #########
     -->
    <nav class="navbar navbar-expand-lg bg-body-tertiary">
        <div class="container">
            <router-link class="navbar-brand" :to="{ name: 'Home' }">Hanwha SW Camp 5th</router-link>

            <ul class="navbar-nav me-auto">
                <li class="nav-item">
                    <router-link class="nav-link active" aria-current="page"
                        :to="{ name: 'Works' }">To-Work</router-link>
                </li>
            </ul>
        </div>
    </nav>


    <div class="container">
        <router-view />
    </div>
    <!-- <router-view/> -->
</template>

<script>


</script>

<style></style>