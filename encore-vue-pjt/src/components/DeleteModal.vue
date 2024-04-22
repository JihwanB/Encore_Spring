<template>
  <Modal>
    <template v-slot:title>
      Delete Work
    </template>
    <template v-slot:body>
      Are U sure?
    </template>
    <template v-slot:footer>
      <button
          type="button"
          class="btn btn-secondary"
          @click="onClose"
      >
        Close
      </button>
      <button
          type="button"
          class="btn btn-danger"
          @click="onDelete"
      >Delete
      </button>
    </template>
  </Modal>
</template>

<script>
import Modal from '@/components/ModalComponent.vue' ;

export default {
  components: {
    Modal
  },
  setup(props, {emit}) {
    const onClose = () => {
      emit('model-close');
    }
    const onDelete = () => {
      emit('model-delete');
    }
    return {
      onClose,
      onDelete
    }
  }
}
</script>

<style>
</style>