<template>
  <div class="card mb-4 shadow-sm program">
    <img class="img-fluid" v-bind:src="program.image">
    <div class="card-body">
      <h4 class="">{{program.title}}</h4>
      <p class="card-text">{{program.summary}}</p>
      <div class="d-flex justify-content-between align-items-center">
        <div class="btn-group">
          <button type="button" class="btn btn-sm btn-outline-secondary">课程详情</button>
        </div>
        <div class="text-muted">
          <span class="badge badge-info"><i class="fa fa-yen" aria-hidden="true"></i>&nbsp;{{program.price}}</span>&nbsp;
          <span class="badge badge-info"><i class="fa fa-user" aria-hidden="true"></i>&nbsp;123</span>&nbsp;
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'the-program',
    props: {
      program: {}
    },
    data: function () {
      return {}
    },
  }
</script>

<style>
  .program h4 {
    font-size: 1.25rem;
    margin: 15px 0;
  }

  .program .text-muted .badge {
    font-size: 1rem;
  }
</style>
