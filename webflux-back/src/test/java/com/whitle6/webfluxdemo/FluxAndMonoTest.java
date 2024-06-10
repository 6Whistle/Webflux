package com.whitle6.webfluxdemo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

// import com.whistle6.webfluxdemo.common.exception.CustomException;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Signal;


public class FluxAndMonoTest {
    // @Mock CustomException customExceptionMono;
    // @Mock CustomException customExceptionFlux;
    
    // @BeforeEach
    // void setUp() {
    //     customExceptionMono = new CustomException("Mono Exception");
    //     customExceptionFlux = new CustomException("Flux Exception");
    // }

    @Test @DisplayName("Test Mono")
    void MonoJustTest(){
        //Reactive Stream 에서는 Data, Event, Signal 중에서 Signal을 사용한다.
        //Signal은 onNext, onComplete, onError가 있다.
        List<Signal<Integer>> list = new ArrayList<>(4);
        final Integer[] result = new Integer[1];
        //Mono는 0~1개의 데이터를 발행한다.
        //just()는 데이터를 발행한다.
        Mono<Integer> mono = Mono.just(1).log()
        .doOnEach(i -> {
            //onNext Signal은 데이터가 발생했을 때 발생한다.
            //onComplete Signal은 데이터가 정상적으로 처리되었을 때 발생한다.
            //onError Signal은 데이터가 처리되다가 에러가 발생했을 때 발생한다.
            list.add(i);
            System.out.println("Signal: " + i.get() + " " + i.getType());
        });
        mono.subscribe(i -> result[0] = i);
        
        assertThat(list.size()).isEqualTo(2);
    }

    @Test @DisplayName("Test Flux")
    void fluxJustTest(){
        List<String> names = new ArrayList<>();
        //Flux는 0~N개의 데이터를 발행한다.
        //just()는 데이터를 순차적으로 발행한다.
        Flux<String> namesFlux = Flux.just("김구", "윤봉길", "안중근").log();
        namesFlux.subscribe(names::add);
        assertThat(names).isEqualTo(List.of("김구", "윤봉길", "안중근"));
        
    }

    /**
    * Flux.create()와 배압
    * Subscriber로부터 요청이 왔을 때(FluxSink#onRequest) 데이터를 전송하거나(pull 방식)
    * Subscriber의 요청에 상관없이 데이터를 전송하거나(push 방식)
    * 두 방식 모두 Subscriber가 요청한 개수보다 더 많은 데이터를 발생할 수 있다.
    * 이 코드는 Subscriber가 요청한 개수보다 3개 데이터를 더 발생한다. 이 경우 어떻게 될까?
    * 기본적으로 Flux.create()로 생성한 Flux는 초과로 발생한 데이터를 버퍼에 보관한다.
    * 버퍼에 보관된 데이터는 다음에 Subscriber가 데이터를 요청할 때 전달된다.
    * 요청보다 발생한 데이터가 많을 때 선택할 수 있는 처리 방식은 다음과 같다.
    * IGNORE : Subscriber의 요청 무시하고 발생(Subscriber의 큐가 다 차면 IllegalStateException 발생)
    * ERROR : 익셉션(IllegalStateException) 발생
    * DROP : Subscriber가 데이터를 받을 준비가 안 되어 있으면 데이터 발생 누락
    * LATEST : 마지막 신호만 Subscriber에 전달
    * BUFFER : 버퍼에 저장했다가 Subscriber 요청시 전달. 버퍼 제한이 없으므로 OutOfMemoryError 발생 가능
    * Flux.create()의 두 번째 인자로 처리 방식을 전달하면 된다.
    * */
    @Test @DisplayName("Flux create() Test")
    void FluxCreateTest(){
        Flux<Integer> flux = Flux.create((FluxSink<Integer> sink) -> {
            sink.onRequest(i -> {
                for(int j = 0; j < i + 3; j++){
                    sink.next(j);
                }
            });
            
        }).log();

        flux.take(10).subscribe(System.out::println);  
    }

    @Test @DisplayName("Flux generate() Test")
    void FluxGenerateTest(){
        Flux<Object> flux = Flux.generate(() -> 0, (state, sink) -> {
            sink.next("3 * " + state + " = " + 3 * state);
            if(state == 10){
                sink.complete();
            }
            return state + 1;
        }).log();
        flux.subscribe(System.out::println);
    }
}
