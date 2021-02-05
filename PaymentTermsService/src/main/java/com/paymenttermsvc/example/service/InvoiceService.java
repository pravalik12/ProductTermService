package com.paymenttermsvc.example.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.paymenttermsvc.example.entity.Invoice;
import com.paymenttermsvc.example.entity.PaymentTerm;
import com.paymenttermsvc.example.repository.InvoiceRepository;
import com.paymenttermsvc.example.repository.PTRepository;

@Service
public class InvoiceService {

	@Autowired
	InvoiceRepository repository;

	@Autowired
	PTRepository ptRepository;

	@Autowired
	PTService paymentservice;

	private static final Logger logger = LoggerFactory.getLogger(InvoiceService.class);

	@Scheduled(cron = "0 24 22 * * *")
	public String runScheduledjob() {

		try {
			logger.info("***************************************");
			logger.info("Scheduler invoked.");
			logger.info("***************************************");

			List<Invoice> al = new ArrayList<Invoice>();
			Invoice invoice = new Invoice();
			al = repository.findAll();
			Iterator<Invoice> itr = al.iterator();
			while (itr.hasNext()) {
				invoice = itr.next();
				if (!(invoice.getStatus().isEmpty())) {
					if (invoice.getStatus().toLowerCase().equals("un paid")) {
						Date date = invoice.getInvoiceDate();
						PaymentTerm pt = (PaymentTerm) paymentservice.getPaymentTermByCode(invoice.getPterm())
								.getData();
						if (isReminder(date, pt.getDays(), pt.getRemindBeforeDays())) {
							logger.info("Reminder is Sent.");
						}
					}
				}
			}

		} catch (Exception e) {
			logger.error(e.getMessage());

		}

		return "Successfully ran the job.";
	}

	public boolean isReminder(Date date, int days, int remindBeforeDays) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		logger.info("Date of Invoice is: {}", sdf.format(cal.getTime()));
		cal.add(Calendar.DAY_OF_MONTH, days);
		cal.add(Calendar.DAY_OF_MONTH, -1 * remindBeforeDays);
		logger.info("Should be reminded on: {}", sdf.format(cal.getTime()));
		Date dt1 = new Date();
		Date dt1withouttime = sdf.parse(sdf.format(dt1));
		Date dt2 = cal.getTime();
		Date dt2withouttime = sdf.parse(sdf.format(dt2));
		if (dt1withouttime.compareTo(dt2withouttime) >= 0) {
			return true;
		}
		return false;
	}

}
