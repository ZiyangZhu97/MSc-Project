<template>
  <main role="main">
    <div class="album py-5 bg-light">
      <div class="container">
        <div class="row">
          <div v-for="o in programs" class="col-md-4">
            <the-program v-bind:program="o"></the-program>
          </div>
          <h3 v-show="programs.length === 0">No program</h3>
        </div>
      </div>
    </div>

  </main>
</template>

<script>
  import TheProgram from "../components/the-program";

  export default {
    components: {TheProgram},
    name: 'list',
    data: function () {
      return {
        programs: [],
      }
    },
    mounted() {
      let _this = this;
      _this.listProgram(1);
    },
    methods: {
      /**
       * 查询课程列表
       */
      listProgram(page) {
        let _this = this;
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/web/program/list', {
          page: page,
          size: 3,
        }).then((response) => {
          let resp = response.data;
          if (resp.success) {
            _this.programs = resp.content.list;
          }
        }).catch((response) => {dto
          console.log("error：", response);
        })
      },

    }
  }
</script>
<style>

</style>