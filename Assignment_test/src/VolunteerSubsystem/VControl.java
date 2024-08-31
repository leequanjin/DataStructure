/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VolunteerSubsystem;

import adt.LinkedList;
import adt.LinkedListInterface;
import adt.Node;
import EventSystem.Event;
import java.util.Scanner;

/**
 *
 * @author Heng Pei Lin
 */
public class VControl {
    
    private static final String VOLUNTEER_PATH = "volunteers.txt";
    private static final String EVENT_PATH = "event.txt";
    private static final String EV_PATH = "volunteer_event.txt";
    
    private static final LinkedListInterface<Volunteer> VOL_LIST = new LinkedList<>();
    private static final LinkedListInterface<Event> EVENT_LIST = new LinkedList<>();
    private static final LinkedListInterface<EventVolunteer> EV_LIST = new LinkedList<>();
    
    public static Scanner scan = new Scanner(System.in);
    
    // -----------
    // Commmon use
    // -----------
    // Validation
    public static boolean chkEmptyInput(String input){
        if(input.isEmpty()){
            return false;
        }else{
            return true;
        }
    }
    
    public static boolean chkInt(String input){
        try {
            int number = Integer.parseInt(input);

            return true;
            
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public static boolean chkLength(int length, String input){
        if(input.length() == length){
            return true;
        }
        return false;
    }
    
    public static boolean intSelectionValidation(int input,int initial, int length){

        if (input < initial || input > length) {
            return false;
        } else {
            return true; 
        }
    }
    
    public static boolean chkPhoneValidation(String phone){
        // 011
        if(phone.substring(0, 3).equals("011") && phone.length() == 11){
            return true;
        }else if( phone.substring(0, 2).equals("01") && phone.length() == 10){ //016 018 017
            return true;
        }else{
            return false;
        }
    }
    
    public static int menuIntReturn(String[] selectionList) {
        
        VBoundary.displayMenu(selectionList);
        
        int intInput = 0;
        boolean validInput = false;

        while (validInput == false) {
            String stringInput = scan.nextLine();

            validInput = chkIntInputInRange(stringInput, 1, selectionList.length);

            if (validInput) {
                intInput = Integer.parseInt(stringInput);
            }

        }

        return intInput;
    }

    public static boolean chkIntInputInRange(String input, int initial, int end) {
        boolean validInput;

        // check is empty
        validInput = chkEmptyInput(input);
        if (validInput) {

            // chk is it an integer
            validInput = chkInt(input);
            if (validInput) {

                // chk is within the integer range
                int intInput = Integer.parseInt(input);
                validInput = intSelectionValidation(intInput, initial, end);
                if (!validInput) {
                    VUtility.intNotInRange(initial, end);
                }

            } else {
                VUtility.invalidIntInput();
            }

        } else {
            VUtility.emptyInputErrorMsg();
        }

        if (!validInput) {
            VBoundary.reEnter();
        }

        return validInput;
    }
    
    // if typr other than specific
    public static boolean chkSpecificWord(String[] inputList, String input){
        for(int i = 0; i < inputList.length; i++){
            if(input.trim().toUpperCase().equalsIgnoreCase(inputList[i].trim().toUpperCase())){
                return true;
            }
        }
        return false;
    }
    
    // only YN
    public static boolean chkYN(String input){
        input = input.toUpperCase().trim();
        if (input.equals("Y")) {
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean YN(String sentence) {

        boolean validInput = false;
        String input = null;

        VBoundary.inputYN(sentence);
        while (!validInput) {

            input = (scan.nextLine()).toUpperCase().trim();

            validInput = chkEmptyInput(input);

            if (validInput) {
                String[] inputList = {"Y", "N"};
                validInput = chkSpecificWord(inputList, input);
                if (!validInput) {
                    VUtility.enterYNOnly();
                }
            } else {
                VUtility.emptyInputErrorMsg();
            }

            if (!validInput) {
                VBoundary.reEnter();
            }

        }

        boolean cont = chkYN(input);
        if (cont) {
            return true;
        } else {
            return false;
        }
    }
    
    public static String idGenerator(String ab){
        
        int maxId = 0;

        Node<Volunteer> current = VOL_LIST.getHead(); 

        while (current != null) {
            String currentId = current.data.getVolunteerID().substring(2,7);
            int idNumber = Integer.parseInt(currentId);
            if (idNumber > maxId) {
                maxId = idNumber;
            }
            current = current.next;
        }
        
        return (ab + String.format("%05d", maxId + 1));
    }
    
    public static boolean idFormat(String id, String constrant){
        if ( (id.substring(0, 2).toUpperCase().equalsIgnoreCase(constrant)) && (id.length() == 7) ){
            return true;
        }else{
            return false;
        }
    }
    
    public static String idValidation(String constrant) {
        boolean validID;
        String id;
        do{
            id = scan.nextLine().trim();
            
            // chk is it empty
            validID = chkEmptyInput(id);
            if (validID) {
                // chk correct length
                validID = chkLength(7, id);
                if (validID) {

                    // chk if correct format
                    validID = idFormat(id, constrant);
                    if (!validID) {
                        VUtility.invalidIDFormat(constrant);
                    }
                } else {
                    VUtility.invalidLength();
                }
            } else {
                VUtility.emptyInputErrorMsg();
            }

            if (!validID) {
                VBoundary.reEnter();
            }
            
        }while(!validID);

        return id.substring(0, 2).toUpperCase() + id.substring(2, 7);
    }
    
    //find the specific volunteer
    public static Volunteer findVolById(String id) {
        Node<Volunteer> current = VOL_LIST.getHead();

        while (current != null) {
            if (current.data.getVolunteerID().equals(id)) {
                return current.data;
            }
            current = current.next;
        }
            return null;
    }
    
    public static boolean deleteVolById(String id) {
        
        if (VOL_LIST.getHead() == null) {
            
            return false;
        } else if (VOL_LIST.getHead().data.getVolunteerID().equals(id)) {
            // First id match
            VOL_LIST.setHead(VOL_LIST.getHead().next);

            return true;
        } else {
            Node<Volunteer> currentNode = VOL_LIST.getHead();

            while (currentNode != null) {

                if (currentNode.next.data.getVolunteerID().equals(id)) {
                    currentNode.next = currentNode.next.next;
                    return true;
                } 
                currentNode = currentNode.next;
            }
             
            return false;
        }
    }
    
    public static void chkFileExist(String[] fileList){
    
        for (int i = 0; i < fileList.length; i++) {
            boolean fileExist = DAO.chkFileExist(fileList[i]);
            if (!fileExist) {
                VUtility.fileNoExist(fileList[i]);
                boolean createFile = DAO.createFile(fileList[i]);
                if (createFile) {
                    VUtility.createFileSuccesfully(fileList[i]);
                } else {
                    VUtility.createFileFail(fileList[i]);
                }
            } else{
                VUtility.fileExist(fileList[i]);
            }
        }
        
    }
    
    public static void clearAllFile(){
        VOL_LIST.clear();
        EVENT_LIST.clear();
        EV_LIST.clear();
    }
    
    public static void loadAllFile(){
        VOL_LIST.loadFromFile(VOLUNTEER_PATH);
        EVENT_LIST.loadFromFile(EVENT_PATH);
        EV_LIST.loadFromFile(EV_PATH);
    }
    
    public static void main (String[] args){
        String[] fileList = {VOLUNTEER_PATH, EVENT_PATH, EV_PATH};
        chkFileExist(fileList);
        volunteerMainMenu();
    }
    
    public static void volunteerMainMenu(){
        boolean cont = true;
        do {

            clearAllFile();
            loadAllFile();
            
            int selection = VBoundary.volunteerMainMenu();
            switch (selection) {
                case 1:
                    addVolunteer();
                    break;
                case 2:
                    remVolunteer();
                    break;
                case 3:
                    searchVolunteer();
                    break;
                case 4:
                    assignEvent();
                    break;
                case 5:
                    searchVolunteerEvent();
                    break;
                case 6:
                    listVolunteer();
                    break;
                case 7:
                    filterVolunteer();
                    break;
                case 8:
                    report();
                    break;
                case 9:
                    return;
                default:
                    VUtility.invalidMenuSelection();
                    break;
            }

            cont = YN("Do you want to continue manage volunteer?");
            if (cont) {
                System.out.println();
            }
        } while (cont);

    }
    
    // -----------------
    // Add new Volunteer
    // -----------------
    public static void addVolunteer() {
        // ID
        String id = VControl.idGenerator("VL");

        // Name
        VBoundary.inputName();
        String name = null;
        boolean validName = false;
        while (!validName) {
            name = scan.nextLine();

            validName = VControl.chkEmptyInput(name);
            if (!validName) {
                VUtility.emptyInputErrorMsg();
                VBoundary.reEnter();
            }
        }

        // Gender
        int gSelection = VBoundary.inputGender();

        String gender = null;
        switch (gSelection) {
            case 1:
                gender = "Male";
                break;
            case 2:
                gender = "Female";
                break;
            default:
                VUtility.invalidGender();
                break;
        }

        // Age
        VBoundary.inputAge();
        int age = 0;
        boolean validAge = false;
        while (validAge == false) {
            String inputAge = scan.nextLine();

            validAge = chkIntInputInRange(inputAge, 18, 60);
            if (validAge) {
                age = Integer.parseInt(inputAge);
            }
        }

        // Contact Num
        VBoundary.inputPhone();
        boolean validPhone = false;
        String phone = null;
        while (!validPhone) {
            phone = scan.nextLine();

            validPhone = VControl.chkEmptyInput(phone);
            if (validPhone) {

                // check is it all integer
                phone = phone.trim();
                validPhone = VControl.chkInt(phone);
                if (validPhone) {

                    //  check correct phone format
                    validPhone = VControl.chkPhoneValidation(phone);
                    if (!validPhone) {
                        VUtility.invalidPhone();
                    }
                } else {
                    VUtility.invalidIntInput();
                }

            } else {
                VUtility.emptyInputErrorMsg();
            }

            if (!validPhone) {
                VBoundary.reEnter();
            }

        }

        Volunteer newVol = new Volunteer(id, name, gender, age, phone);
        VOL_LIST.insert(newVol);
        
        VBoundary.displayVol(VOL_LIST);
        VOL_LIST.saveToFile(VOLUNTEER_PATH);
    }
    
    // -----------------
    // Remove Volunteer
    // -----------------
    public static void remVolunteer() {
        
        VBoundary.disRemVolunteer();
        VBoundary.displayVol(VOL_LIST);
        VBoundary.breakLine();
        
        if (VOL_LIST.isEmpty()) {
            VUtility.noVolInList();
            return;
        } else {
            VOL_LIST.show();
        }

        boolean cont;
        do{
        
            String id = VBoundary.inputVolID();

            Volunteer foundVolunteer = findVolById(id);
            if (foundVolunteer == null) {
                VUtility.volNoExist();
                cont = YN("Do you want to remove other volunteer?");
                VBoundary.breakLine();
            } else {
                boolean remove = VControl.deleteVolById(id);
                cont = false;
                if (remove == false) {
                    VUtility.remVolFail();
                } else {
                    VUtility.remVolSuccess();
                    VBoundary.displayVol(VOL_LIST);
                    VOL_LIST.saveToFile(VOLUNTEER_PATH);
                }
            }
            
        } while(cont);
    }
    
    // -----------------
    // Search Volunteer
    // -----------------
    public static void searchVolunteer() {
        
        VBoundary.disSearchVolunteer();
        if (VOL_LIST.isEmpty()) {
            VUtility.noVolInList();
            return;
        }
        
        boolean cont;
        do{
            String id = VBoundary.inputVolID();

            Volunteer foundVolunteer = findVolById(id);
            if (foundVolunteer == null) {
                VUtility.volNoExist();
                cont = YN("Do you want to remove other volunteer?");
                VBoundary.breakLine();
            } else {
                cont = false;
                VBoundary.disVolHeader();
                VBoundary.disCertainVolunteer(foundVolunteer);
            }
        }while(cont);
    }
    
    // -----------------
    // Assign volunteer event
    // -----------------
    public static void assignEvent() {
        
        VBoundary.disAssignEvent();
        
        if (EVENT_LIST.isEmpty()) {
            VUtility.noEventInList();
            return;
        }

        if (VOL_LIST.isEmpty()) {
            VUtility.noVolInList();
            return;
        }

        VBoundary.breakLine();
        VBoundary.displayEventTable(EVENT_LIST);
        VBoundary.breakLine();

        String eventID = VBoundary.inputEventID();
        
        
        boolean contAddVol = volHavenAttendEvent(eventID);
        if(!contAddVol){
            return;
        }
        
        String volID = VBoundary.inputVolID();
        Volunteer foundVolunteer = findVolById(volID);
        if (foundVolunteer == null) {
            VUtility.volNoExist();
            return;
        } 
        
        EventVolunteer ev = new EventVolunteer(eventID, volID);
        EV_LIST.insert(ev);
        EV_LIST.saveToFile(EV_PATH);
        
        VUtility.volAsssignEvent();
        
        if (EV_LIST.isEmpty()) {
            VUtility.noEVInList();
            return;
        }
        
        VBoundary.displayEVTable(EV_LIST);
    }
    
    public static<T> boolean volHavenAttendEvent(String eventID) {

        boolean contAdd = true;

        VOL_LIST.removeEmptyData();
        EV_LIST.removeEmptyData();
        
        // if combine list empty
        if (EV_LIST.isEmpty()) {

            VBoundary.displayVol(VOL_LIST);
            
        } else {
            Node<Volunteer> volCurrentNode = VOL_LIST.getHead();
            int sum = 0;
            int show = 0;
            while (volCurrentNode != null) {
                boolean isInvolved = false;

                // Check if the volunteer is involved in the event
                Node<EventVolunteer> combineCurrentNode = EV_LIST.getHead();
                while (combineCurrentNode != null) {
                    EventVolunteer eventVolunteer = combineCurrentNode.data;
                    if (eventVolunteer.getEventID().equals(eventID) && eventVolunteer.getVolunteerID().equals(volCurrentNode.data.getVolunteerID())) {
                        isInvolved = true;
                        sum++;
                        break;
                    }
                    combineCurrentNode = combineCurrentNode.next;
                }

                // If the volunteer is not involved in the event, print their details
                if (!isInvolved) {
                    if(show == 0){
                        VBoundary.breakLine();
                        VBoundary.disVolHeader();
                        show++;
                    }
                    VBoundary.disCertainVolunteer(volCurrentNode.data);
                }

                volCurrentNode = volCurrentNode.next;
            }
            
            // all volunteer had been assign
            if(sum == VOL_LIST.length()){
                VUtility.allVolAttendEvent();
                contAdd = false;
            }
        }
        
        VBoundary.breakLine();
        return contAdd;
    }
    
    // ----------------------
    // Search volunteer event
    // ----------------------
    public static void searchVolunteerEvent() {
        VBoundary.disSearchVolunteerEvent();
        
        String volID = VBoundary.inputVolID();
        Volunteer foundVolunteer = findVolById(volID);
        if (foundVolunteer == null) {
            VUtility.volNoExist();
            return;
        } 

        disVolAttendedEvent(volID);
        
    }
    
    public static void disVolAttendedEvent(String volID){
        VBoundary.disCurentVolunteer(volID);
        Node<EventVolunteer> evNode = EV_LIST.getHead();
        int show = 1;
        while(evNode != null){
            if (evNode.data.getVolunteerID().equals(volID)){
                VBoundary.disVolCEventID(show, evNode.data.getEventID());
                Node<Event> event = EVENT_LIST.getHead();
                while(event != null){
                    if(event.data.getEventID().equals(evNode.data.getEventID())){
                        VBoundary.disVolCEventName(event.data.getEventName());
                    }
                    event = event.next;
                }
                show++;
            }
            evNode = evNode.next;
        }
        if (show == 1){
            VUtility.volNoAttendEvent();
        }
    }
    
    // --------------
    // List Volunteer
    // ---------------
    public static void listVolunteer(){
        VBoundary.displayVol(VOL_LIST);
    }
    
    // ---------------------------------
    // Filter Volunteer base on Criteria
    // ---------------------------------
    public static void filterVolunteer(){
        VBoundary.disFilterVolunteer();
        if(VOL_LIST.isEmpty()){
            VUtility.noVolInList();
            return;
        }
        
        int filterSelection = VBoundary.filterMainMenu();
        
        switch(filterSelection){
            case 1:
                filterGenderSelection();
                break;
            case 2:
                filterAge();
                break;
            default:
                VUtility.invalidMenuSelection();
                break;
        }
    }
    
    public static void filterGenderSelection(){
        int filterSelection = VBoundary.disFilterGenderSelection();
        
        switch(filterSelection){
            case 1:
                filterGender(true);
                break;
            case 2:
                filterGender(false);
                break;
            default:
                VUtility.invalidMenuSelection();
                break;
        }
    }
    
    public static void filterGender(boolean isMale){
        Node<Volunteer> currentNode = VOL_LIST.getHead();
        int show = 1;
        String gender = null;
        while(currentNode != null){
            if(isMale == true){
                gender = "male";
                if(currentNode.data.getGender().toUpperCase().equalsIgnoreCase("MALE")){
                    if(show == 1){
                        VBoundary.maleHeading();
                        VBoundary.disVolHeader();
                    }
                    VBoundary.disCertainVolunteer(currentNode.data);
                    show++;
                }
            }else{
                gender = "female";
                if(currentNode.data.getGender().toUpperCase().equalsIgnoreCase("FEMALE")){
                    if(show == 1){
                        VBoundary.femaleHeading();
                        VBoundary.disVolHeader();
                    }
                    VBoundary.disCertainVolunteer(currentNode.data);
                    show++;
                }
            }
            currentNode = currentNode.next;
        }
        if(show == 1){
            VUtility.noCertainGenderVolunteer(gender);
        }
    }
    
    public static void filterAge(){
        VBoundary.disFilterAge();
        
        int age = 0;
        boolean validAge = false;
        while(!validAge){
            String inputA = scan.nextLine();
            
            validAge = chkIntInputInRange(inputA, 18, 60);
            
            if(validAge){
                age = Integer.parseInt(inputA);
            }
        }
        
        Node<Volunteer> currentNode = VOL_LIST.getHead();
        int show = 1;
        while(currentNode != null){
            if(currentNode.data.getAge() <= age){
                if(show == 1){
                    VBoundary.disAgeHeader(age);
                    VBoundary.disVolHeader();
                }
                VBoundary.disCertainVolunteer(currentNode.data);
                show++;
            }
            currentNode = currentNode.next;
        }
        
        if(show == 1){
            VUtility.noCertainVolBelowAge();
        }
        
    }
    
    // ---------------
    // Report Generate
    // ---------------
    public static void report(){
        VBoundary.disReport();
        if(VOL_LIST.isEmpty()){
            VUtility.noDataForReport();
            return;
        }
        
        int reportSelection = VBoundary.disReportMenu();
        
        switch(reportSelection){
            case 1:
                genderDistribution();
                break;
            case 2:
                volAgeGroup();
                break;
            case 3:
                repeatVol();
                break;
            default:
                VUtility.invalidMenuSelection();
                break;
        }
    }
    
    public static void genderDistribution(){
        Node<Volunteer> currentNode = VOL_LIST.getHead();
        
        int sumM = 0;
        int sumF = 0;
        while(currentNode!=null){
            
            String gender = currentNode.data.getGender();
            if(gender.toUpperCase().equals("MALE")){
                sumM++;
            }else{
                sumF++;
            }
            
            currentNode = currentNode.next;
        }
        
        VBoundary.disGenderDistribution(sumM, sumF);
    }
    
    public static void volAgeGroup(){
        Node<Volunteer> currentNode = VOL_LIST.getHead();
        
        int sumY = 0; // 18 - 30
        int sumM = 0; // 31 - 45
        int sumO = 0; // 45 - 60
        while(currentNode!=null){
            
            int age = currentNode.data.getAge();
            if(age <= 30){
                sumY++;
            }else if (age <= 45){
                sumM++;
            }else{
                sumO++;
            }
            
            currentNode = currentNode.next;
        }
        
        int max = sumY;
        String group ="young adults";
        if(max < sumM){
            max = sumM;
            group = "middle-aged adults";
        }
        if(max < sumY){
            max = sumY;
            group = "old-aged adults";
        }
        
        VBoundary.disVolAgeGroup(sumY, sumM, sumO);
        VBoundary.ageGroupConclu(group, max);
    }
    
    public static void printStar(int count){
        if (count > 50){
            int left = count % 50;
            for (int i = 0; i < left; i ++){
               VBoundary.disStar();
            }
        }
    }
    
    public static void repeatVol() {
        LinkedListInterface<Volunteer> volList = new LinkedList<>();
        LinkedListInterface<Volunteer> dupList = new LinkedList<>();

        Node<Volunteer> currentNode = VOL_LIST.getHead();

        while (currentNode != null) {
            Volunteer currentVolunteer = currentNode.data;
            if (volList.contains(currentVolunteer)) {
                dupList.insert(currentVolunteer);
            } else {
                volList.insert(currentVolunteer);
            }
            currentNode = currentNode.next;
        }

        if(!dupList.isEmpty()){
            VBoundary.displayDuplicateVol(dupList);
        }else{
            VBoundary.displayNoDup();
        }

    }
    
}