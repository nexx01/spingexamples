package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.jmx.export.notification.NotificationPublisher;
import org.springframework.jmx.export.notification.NotificationPublisherAware;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.Notification;
import java.util.concurrent.atomic.AtomicLong;

@RestController("/")
@RequiredArgsConstructor
@ManagedResource
public class SampleController implements NotificationPublisherAware {

    AtomicLong integer =new AtomicLong();
    @Autowired
    SomeEntityRepository someEntityRepository;
    private NotificationPublisher np;

    @GetMapping("some")
    @ManagedOperation
    public void increment() {
        someEntityRepository.save(new SomeEntity(3,"dcasdcsa"));
    }

    @GetMapping("jmxT")
    @ManagedOperation
    public long jmxT() {

        long before = integer.get();
        long after = integer.addAndGet(2);
        Notification notification = new Notification(
                "Intejer.count", this,
                before, after + "th taco created!"
        );
        np.sendNotification(notification);

    return after;
    }

    @Override
//    @Autowired
    public void setNotificationPublisher(final NotificationPublisher notificationPublisher) {
        this.np = notificationPublisher;
    }
}
