<template>

    <!-- 카드 목록에서 삭제 이벤트 발생 시 -->
    <div class="modal-wrapper">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">WARNING</h5>
                    <button type="button" class="btn" data-bs-dismiss="modal" aria-label="Close">
                        <span @click="onClose">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <p>The selected record will be deleted. Are you sure to proceed?</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" @click="onClose">No</button>
                    <button type="button" class="btn btn-primary" @click="onDelete">Yes</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    emits: [
        'modal-close',
        'modal-delete'
    ],

    setup(props, { emit }) {

        const onClose = () => {
            emit('modal-close');
        }

        const onDelete = () => {
            emit('modal-delete');
            onClose();
        }

        return {
            onClose,
            onDelete,
        }

    }
}
</script>

<style scope>
.modal-wrapper {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5);
    z-index: 100;
    display: flex;
    justify-content: center;
    align-items: center;
}
</style>