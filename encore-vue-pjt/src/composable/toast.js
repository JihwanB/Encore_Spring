// import {ref, onUnmounted, computed} from 'vue';
import {onUnmounted, computed} from 'vue';
import {useStore} from 'vuex';

export const useToast = () => {
    // vuex를 이용해서 4개의 reactive state 값을 공유할 수 있도록 구현
    const store = useStore();
    const showToast = computed(() => {
        return store.state.showToast
    });
    const toastMessage = computed(() => {
        return store.state.toastMessage
    });
    const toastAlertType = computed(() => {
        return store.state.toastAlertType
    });
    const timeOut = computed(() => {
        return store.state.timeOut
    });

    // toast 위한 변수 추가
    // const showToast = ref(false);
    // const toastMessage = ref('');
    // const toastAlertType = ref('');
    // 메모리 누수방지를 위한 변수 추가
    // const timeOut = ref(null);

    onUnmounted(() => {
        console.log('WorkView onUnmounted >>>>>');
        // javascript clearTimeout();
        clearTimeout(timeOut.value);
    })
    const triggerToast = (message, type = 'success') => {
        // store 에 있는 액션을 실행해서 공유상태값을 반영
        // mutaion 변경, action 반영
        // action을 실행하기 위해서 dispatch()
        store.dispatch('triggerToast', message, type);

        // vuex 이동되었기 때문에 주석처리를 하고
        // console.log(">>> composables >>>> toast.js triggerToast");
        // 아래코드 부분이 vuex 관리되는 상태값이여야 된다.
        // showToast.value = true;
        // toastMessage.value = message;
        // toastAlertType.value = type;

        // dom unmounted 되면 toast 실행 멈추게함
        // timeOut.value = setTimeout(() => {
        //     console.log(">>> setTimeOut <<<");
        //     showToast.value = false;
        //     toastMessage.value = '';
        //     toastAlertType.value = '';
        // }, 5000);
    }
    return {
        showToast,
        toastMessage,
        toastAlertType,
        triggerToast
    }
}