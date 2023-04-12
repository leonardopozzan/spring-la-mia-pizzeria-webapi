<template>

  <header>
    <HeaderComponent/>
  </header>
  <main>
    <FormComponent />
    <MenuComponent />
  </main>
</template>

<script>
import FormComponent from './components/FormComponent.vue';
import HeaderComponent from './components/HeaderComponent.vue';
import MenuComponent from './components/MenuComponent.vue';


  export default {
    components: { HeaderComponent, MenuComponent, FormComponent }
}
</script>

<style lang="scss" scoped>

</style>