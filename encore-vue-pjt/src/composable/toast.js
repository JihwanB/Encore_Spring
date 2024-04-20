import { ref, onUnmounted } from 'vue';

export const useToast = () => {
    // toast 위한 변수 추가
    const showToast = ref(false);
    const toastMessage = ref('');
    const toastAlertType = ref('');
    // 메모리 누수방지를 위한 변수 추가
    const timeOut = ref(null);
    onUnmounted(() => {
        console.log('WorkView onUnmounted >>>>>');
        // javascript clearTimeout();
        clearTimeout(timeOut.value);
    })
    const triggerToast = (message, type = 'success') => {
        showToast.value = true;
        toastMessage.value = message;
        toastAlertType.value = type;

        // dom unmounted 되면 toast 실행 멈추게함
        timeOut.value = setTimeout(() => {
            console.log(">>> setTimeOut <<<");
            showToast.value = false;
            toastMessage.value = '';
            toastAlertType.value = '';
        }, 5000);
    }
    return {
        showToast,
        toastMessage,
        toastAlertType,
        triggerToast
    }
}