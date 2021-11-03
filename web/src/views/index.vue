<template>
  <main role="main">

    <section class="jumbotron text-center">
      <div class="container">
        <h1>Online Video on Demand</h1>
        <p class="lead text-muted m-3">
          You can Watch movies and series by One Click on the browser!
        </p>
        <p>
          <a href="#" class="btn btn-primary my-2 p-3">Click to Find a Program</a>
        </p>
      </div>
    </section>

    <div class="album py-5 bg-light">
      <div class="container">
        <div class="title1">New Programs</div>

        <div class="row">
          <div v-for="o in news" class="col-md-4">
            <div class="card mb-4 shadow-sm" program>
              <img class="img-fluid" v-bind:src="o.image">
              <div class="card-body">
                <h4 class="">{{o.title}}</h4>
                <p class="card-text">{{o.summary}}</p>
                <div class="d-flex justify-content-between align-items-center">
                  <div class="btn-group">
                    <button type="button" class="btn btn-sm btn-outline-secondary">Details</button>
                  </div>
                  <div class="text-muted">
                    <span class="badge badge-info"><i class="fa fa-yen" aria-hidden="true"></i>&nbsp;{{o.price}}</span>&nbsp;
                    <span class="badge badge-info"><i class="fa fa-user" aria-hidden="true"></i>&nbsp;123</span>&nbsp;
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>

        <hr>

        <div class="title2">Popular Programs</div>
        <div class="row">
          <div v-for="o in news" class="col-md-4">
            <the-program v-bind:program="o"></the-program>
          </div>
        </div>
      </div>
    </div>

  </main>
</template>

<script>
  import TheProgram from "../components/the-program";

  export default {
    name: 'index',
    components: {TheProgram},
    data: function () {
      return {
        news: [],
      }
    },
    mounted() {
      let _this = this;
      _this.listNew();
    },
    methods: {
      /**
       * 查询新上好课
       */
      listNew() {
        let _this = this;
        _this.$ajax.get(process.env.VUE_APP_SERVER + '/business/web/program/list-new').then((response)=>{
          console.log("查询新上节目结果：", response);
          let resp = response.data;
          if (resp.success) {
            _this.news = resp.content;
          }
        }).catch((response)=>{
          console.log("error：", response);
        })
      },
    }
  }
</script>
<style>
  .title1{
    margin-bottom: 2rem;
    color: transparent;
    -webkit-text-stroke: 1px black;
    letter-spacing: 0.04em;
    font-size: 2rem;
  }
  .title2{
    margin-bottom: 2rem;
    color: transparent;
    -webkit-text-stroke: 1px black;
    letter-spacing: 0.04em;
    font-size: 2rem;
  }


</style>
