package com.analitics.managerialstaff.ui.view.navigations.certification;

import com.analitics.managerialstaff.backend.model.Certification;
import com.analitics.managerialstaff.backend.model.Employee;
import com.analitics.managerialstaff.backend.model.enums.CertificationYear;
import com.analitics.managerialstaff.backend.model.enums.Quarter;
import com.analitics.managerialstaff.ui.common.NotificationManager;
import com.analitics.managerialstaff.ui.components.events.certifications.CertificationAddEvent;
import com.analitics.managerialstaff.ui.components.events.certifications.CertificationDeleteEvent;
import com.analitics.managerialstaff.ui.components.events.certifications.CertificationEditEvent;
import com.analitics.managerialstaff.ui.theme.MyTheme;
import com.analitics.managerialstaff.ui.view.navigations.certification.dto.CertificationDTO;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.annotation.VaadinUIScope;
import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.events.EventScope;
import org.vaadin.spring.navigator.annotation.VaadinView;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.fields.MTable;
import org.vaadin.viritin.fields.MValueChangeEvent;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import javax.annotation.PostConstruct;
import java.util.Collection;

/**
 * @author by nikolai.pashkevich
 */
@VaadinUIScope
@VaadinView(name = CertificationsView.NAME)
public class CertificationsViewImpl extends VerticalLayout implements CertificationsView {

    @Autowired
    private EventBus.UIEventBus eventBus;

    @Autowired
    private NotificationManager notificationManager;

    private ComboBox yearSelect;
    private ComboBox quarterSelect;
    private MHorizontalLayout controlButtonsLayout;
    private Button addCertificationButton;
    private Button deleteCertificationButton;
    private Button editCertificationButton;
    private MTable<CertificationDTO> certificationTable;

    @PostConstruct
    private void init() {
        setSizeFull();

        initComponents();
        constructLayout();
    }

    private void initComponents() {
        initSelectComponents();
        initButtons();
        initTable();
    }

    private void initSelectComponents() {
        yearSelect = new ComboBox("Год");
        yearSelect.setNullSelectionAllowed(false);
        yearSelect.setWidth(100, Unit.PIXELS);
        yearSelect.addItems(
                CertificationYear.YEAR_2015,
                CertificationYear.YEAR_2014,
                CertificationYear.YEAR_2013,
                CertificationYear.YEAR_2012
        );
        yearSelect.select(CertificationYear.YEAR_2015);

        quarterSelect = new ComboBox("Квартал");
        quarterSelect.setNullSelectionAllowed(false);
        quarterSelect.setWidth(80, Unit.PIXELS);
        quarterSelect.addItems(
                Quarter.FIRST,
                Quarter.SECOND,
                Quarter.THIRD,
                Quarter.FOURTH
        );
        quarterSelect.select(Quarter.FIRST);
    }

    private void initButtons() {
        addCertificationButton = new MButton("Добавить", this::addCertification).withIcon(FontAwesome.PLUS);
        editCertificationButton = new MButton("Изменить", this::editEmployee).withIcon(FontAwesome.PENCIL);
        deleteCertificationButton = new MButton("Удалить", this::deleteCertification).withIcon(FontAwesome.TRASH_O);

        controlButtonsLayout = new MHorizontalLayout(
                addCertificationButton,
                editCertificationButton,
                deleteCertificationButton
        ).withMargin(false);
    }

    private void initTable() {
        certificationTable = new MTable<>(CertificationDTO.class);
        certificationTable.withCaption("Аттестация сотрудников");
        certificationTable.addMValueChangeListener(this::tableElementSelection);
        certificationTable.setImmediate(true);
        certificationTable.setWidth(100, Unit.PERCENTAGE);
        setupTableColumns();
    }

    private void setupTableColumns() {
        certificationTable.withProperties(
                CertificationDTO.SURNAME,
                CertificationDTO.FORENAME,
                CertificationDTO.POSITION,
                CertificationDTO.YEAR,
                CertificationDTO.QUARTER,
                CertificationDTO.RESPONSIBILITY,
                CertificationDTO.COMPETENCE,
                CertificationDTO.COMMUNICABILITY,
                CertificationDTO.TEST_RESULT
        );
        certificationTable.withColumnHeaders(
                "Фамилия",
                "Имя",
                "Должность",
                "Год аттестации",
                "Квартал аттестации",
                "Ответственность",
                "Компетентность",
                "Коммуникабельность",
                "Результаты теста"
        );
    }

    private void constructLayout() {
        MVerticalLayout resultLayout = new MVerticalLayout(
                new MHorizontalLayout(
                        yearSelect,
                        quarterSelect
                ).withMargin(false),
                controlButtonsLayout,
                certificationTable
        ).expand(certificationTable);
        addComponent(resultLayout);
    }

    @Override
    public void setCertifications(Iterable<CertificationDTO> certifications) {
        certificationTable.removeAllItems();
        certificationTable.addBeans((Collection<CertificationDTO>) certifications);
    }

    @Override
    public void emptyCertificationNotification() {
        notificationManager.showNotification("Выберете аттестацию из таблицы", MyTheme.NOTIFICATION_WARNING);
    }

    @Override
    public void saveCertificationSuccessNotification() {
        notificationManager.showNotification("Новая аттестация успешно создана", MyTheme.NOTIFICATION_SUCCESS);
    }

    @Override
    public void editCertificationSuccessNotification() {
        notificationManager.showNotification("Данные аттестации успешно изменены", MyTheme.NOTIFICATION_SUCCESS);
    }

    @Override
    public void deleteCertificationSuccessNotification() {
        notificationManager.showNotification("Аттестация успешно удалена", MyTheme.NOTIFICATION_SUCCESS);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        editCertificationButton.setEnabled(false);
        deleteCertificationButton.setEnabled(false);
    }

    private void addCertification(Button.ClickEvent event) {
        eventBus.publish(EventScope.UI, this, new CertificationAddEvent());
    }

    private void editEmployee(Button.ClickEvent event) {
        CertificationDTO selectedCertification = certificationTable.getValue();
        if (selectedCertification != null && certificationTable.getItemIds().contains(selectedCertification)) {
            eventBus.publish(EventScope.UI, this, new CertificationEditEvent(selectedCertification));
        } else {
            emptyCertificationNotification();
        }
    }

    private void deleteCertification(Button.ClickEvent event) {
        // todo confirmation dialog
        CertificationDTO selectedCertification = certificationTable.getValue();
        if (selectedCertification != null && certificationTable.getItemIds().contains(selectedCertification)) {
            eventBus.publish(EventScope.UI, this, new CertificationDeleteEvent(selectedCertification));
        } else {
            emptyCertificationNotification();
        }
    }

    private void tableElementSelection(MValueChangeEvent event) {
        editCertificationButton.setEnabled(event.getValue() != null);
        deleteCertificationButton.setEnabled(event.getValue() != null);
    }
}
