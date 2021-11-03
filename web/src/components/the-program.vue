<template>
  <div class="card mb-4 shadow-sm program">
    <img class="img-fluid" v-bind:src="program.image">
    <div class="card-body">
      <h4 class="">{{program.title}}</h4>
      <p class="card-text">{{program.summary}}</p>
      <div class="d-flex justify-content-between align-items-center">
        <div class="btn-group">
          <button type="button" class="btn btn-sm btn-outline-secondary">Details</button>
        </div>
        <div class="text-muted">
          <span class="badge badge-info"><i class="fa fa-gbp" aria-hidden="true"></i>&nbsp;{{program.price}}</span>&nbsp;
          <span class="badge badge-info"><i class="fa fa-heart" aria-hidden="true"></i>&nbsp;123</span>&nbsp;
          <span class="badge badge-info">{{PROGRAM_TYPE | optionKV(program.type)}}</span>

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
      return {
        PROGRAM_TYPE: PROGRAM_TYPE
      }
    },
  }
</script>

<style>
  .program h4 {
    font-size: 1.25rem;
    margin: 15px 0;
  }

  .program .text-muted .badge {
    font-size: 0.9rem;
  }
</style>
