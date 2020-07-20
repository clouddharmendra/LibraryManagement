package com.fsd.onlinelibrarymanagement.listener;

import com.fsd.onlinelibrarymanagement.model.SelectedBooks;
import com.fsd.onlinelibrarymanagement.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Component
public class UserModelListener extends AbstractMongoEventListener<SelectedBooks> {

	private SequenceGeneratorService sequenceGeneratorService;

	@Autowired
	public UserModelListener(SequenceGeneratorService sequenceGeneratorService) {
		this.sequenceGeneratorService = sequenceGeneratorService;
	}

	@Override
	public void onBeforeConvert(BeforeConvertEvent<SelectedBooks> event) {
		if (event.getSource().getId() < 1) {
			event.getSource().setId(sequenceGeneratorService.generateSequence("database_sequences"));
		}

	}
}